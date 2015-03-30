/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.seats.RestSeatFacade;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.web.model.Seat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SeatsController {
  private List<Seat> seats;
  private List<Seat> originalSeats;
  private List<String> selectedSeats;
  private Seat seat;
  private RestSeatFacade restSeatFacade;
  
  @PostConstruct
  public void init() {
    seats = new ArrayList();
    originalSeats = new ArrayList();
    selectedSeats = new ArrayList();
    seat = new Seat();
    List<SeatTO> seatsTransfer;
    try {
      restSeatFacade = new RestSeatFacade();
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
  
  public void buySeats(String conferenceId, String assistantId) {
    System.out.println("SILLAS ESCOGIDAS CONF: " + conferenceId +
                      "\nAssistant: " + assistantId);
    for (int i = 0; i < selectedSeats.size(); ++i) {
      System.out.println(selectedSeats.get(i));
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
}
