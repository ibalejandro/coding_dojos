/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.seats.RestSeatFacade;
import co.edu.eafit.conferre.support.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.TransactionException;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Seat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "seatsController")
@SessionScoped
public class SeatsController {
  private List<Seat> seats;
  private List<Seat> originalSeats;
  private List<String> selectedSeats;
  private List<Seat> seatsToBeBought;
  private Seat seat;
  private RestSeatFacade restSeatFacade;
  private double amountToPay;
  private FacesContext context;
  
  @PostConstruct
  public void init() {
    seats = new ArrayList();
    originalSeats = new ArrayList();
    selectedSeats = new ArrayList();
    seatsToBeBought = new ArrayList();
    seat = new Seat();
    restSeatFacade = new RestSeatFacade();
    retrieveSeats();
  }
  
  public void retrieveSeats() {
    originalSeats.clear();
    seats.clear();
    selectedSeats.clear();
    seatsToBeBought.clear();
    List<SeatTO> seatsTransfer;
    try {
      seatsTransfer = restSeatFacade.findSeats(null);
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      return;
    }
    for (SeatTO seatItem : seatsTransfer) {
      Seat newSeat = new Seat();
      newSeat.update(seatItem);
      originalSeats.add(newSeat);
      seats.add(newSeat);
    }
  }
  
  public void setupSeats(int capacity) {
    Collections.sort(originalSeats);
    if (capacity < originalSeats.size()) seats = originalSeats.subList(0, capacity);
    else seats = originalSeats.subList(0, originalSeats.size());
  }
  
  public void buySeats(String assistantId) {
    seatsToBeBought.clear();
    amountToPay = 0.0;
    for (int i = 0; i < selectedSeats.size(); ++i) {
      for (int j = 0; j < seats.size(); ++j) {
        if (selectedSeats.get(i).equals(seats.get(j).getId())) {
          Seat s = new Seat(seats.get(j));
          s.setAssistantId(assistantId);
          //s.setAvailable(false);
          seatsToBeBought.add(s);
          amountToPay += (s.getType().equals("VIP") ? 15000.0 : 10000.0);
          break;
        }
      }
    }
  }
  
  public void updateAndSaveSeatsState(boolean available) {
    for (int i = 0; i < seatsToBeBought.size(); ++i) {
      Seat s = seatsToBeBought.get(i);
      String assistantId = s.getAssistantId();
      //Assistant ID ya se había puesto arriba.
      if (!available) s.setAvailable(false);
      else {
        s.setAvailable(true);
        s.setAssistantId(null);
      }
      SeatTO seatTransfer = new SeatTO(s.getId(), s.getNumber(), s.getType(),
              s.isAvailable(), s.getSpaceId(), s.getAssistantId());
      try {
        SeatTO resultSeat = restSeatFacade.reserve(seatTransfer);
        if (resultSeat != null) {
          s.update(resultSeat);
          s.setAssistantId(assistantId);
          seatsToBeBought.set(i, s);
        }
      }
      catch (UnitOfWorkException ex) {
        System.err.println("Error: " + ex.getMessage());
        ex.printStackTrace();
        String message;
        if (ex instanceof ValidationException) message = ex.getMessage();
        else message = "An error has occurred";
        showMessage("Error", message);
        return;
      }
    }
  }
  
  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }
  public List<String> getSelectedSeats() {
    return selectedSeats;
  }

  public void setSelectedSeats(List<String> selectedSeats) {
    this.selectedSeats = selectedSeats;
  }

  public List<Seat> getSeatsToBeBought() {
    return seatsToBeBought;
  }

  public void setSeatsToBeBought(List<Seat> seatsToBeBought) {
    this.seatsToBeBought = seatsToBeBought;
  }

  public double getAmountToPay() {
    return amountToPay;
  }

  public void setAmountToPay(double amountToPay) {
    this.amountToPay = amountToPay;
  }
  
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}
