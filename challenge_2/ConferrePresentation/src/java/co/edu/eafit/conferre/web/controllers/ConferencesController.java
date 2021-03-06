/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.conferences.RestConferenceFacade;
import co.edu.eafit.conferre.support.to.ConferenceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Conference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ConferencesController {
  private Conference conference;
  private List<Conference> conferences;
  private RestConferenceFacade restConferenceFacade;
  private FacesContext context;
  
  @PostConstruct
  public void init() {
    conference = new Conference();
    conferences = new ArrayList();
    restConferenceFacade = new RestConferenceFacade();
    try {
      List<ConferenceTO> conferencesTransfer;
      conferencesTransfer = restConferenceFacade.findConferences(null);
      for (ConferenceTO conferenceItem : conferencesTransfer) {
        Conference newConference = new Conference();
        newConference.update(conferenceItem);
        conferences.add(newConference);
      }
    }
    catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      String message;
      if (ex instanceof ValidationException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
  }
  
  public void click(String renterId) {
    conference.setRenterId(renterId);
    ConferenceTO newConference = new ConferenceTO(
            conference.getId(), conference.getName(),
            conference.getLecturerName(), conference.getType(),
            conference.getDate(), conference.getAvailableSeats(),
            conference.getRenterId());

    ConferenceTO result;
    try {
      result = restConferenceFacade.createConference(newConference);
    }
    catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      
      String message;
      if (ex instanceof ValidationException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
    
    conference.update(result);
    //RequestContext requestContext = RequestContext.getCurrentInstance();
    //requestContext.update("form:display");
    //requestContext.execute("PF('dlg').show()");
    try {
      context = FacesContext.getCurrentInstance();
      context.getExternalContext().redirect("create_event.jsf");
    } catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }
  }
  
  public void showConferenceDetails(Conference clickedConference) {
    conference = clickedConference;
    try {
      context = FacesContext.getCurrentInstance();
      context.getExternalContext().redirect("show_conference.jsf");
    } catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }
  }
  
  public Conference getConference() {
    return conference;
  }
  public void setConference(Conference conference) {
    this.conference = conference;
  }

  public List<Conference> getConferences() {
    return conferences;
  }

  public void setConferences(List<Conference> conferences) {
    this.conferences = conferences;
  }
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}
