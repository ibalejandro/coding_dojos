/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.events.RestEventFacade;
import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Conference;
import co.edu.eafit.conferre.web.model.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class EventsController {
  private List<Event> events;
  private Event event;
  private RestEventFacade restEventFacade;
  private FacesContext context;
  
  @PostConstruct
  public void init() {
    events = new ArrayList();
    event = new Event();
    restEventFacade = new RestEventFacade();
  }
  
  public void click(String spaceId) {
    EventTO newEvent = new EventTO(
            event.getId(), event.getName(),
            event.getType(), event.getDescription(),
            event.getDate(), event.getAvailableSeats(),
            event.getConferenceId());

    EventTO result;
    try {
      result = restEventFacade.createEvent(newEvent);
    }
    catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      
      String message;
      if (ex instanceof ValidationException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
    if (spaceId != null && !spaceId.isEmpty()) {
      SpaceTO space = new SpaceTO();
      space.setId(spaceId);
      space.setEventId(result.getId());
      try {
        restEventFacade.associateSpace(space);
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
    else {
      System.out.println("No se seleccionó espacio");
    }
    event.update(result);
    events.add(new Event(event));
    event.clearFields();
    showMessage("Éxito", "El evento fue creado satisfactoriamente");
    //RequestContext requestContext = RequestContext.getCurrentInstance();
    //requestContext.update("form:display");
    //requestContext.execute("PF('dlg').show()");
    /*try {
      context = FacesContext.getCurrentInstance();
      context.getExternalContext().redirect("create_event.jsf");
    } catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }*/
  }
  
  public void retrieveConferenceEvents(String conferenceId) {
    events.clear();
    event.setConferenceId(conferenceId);
    EventTO param = new EventTO();
    param.setConferenceId(conferenceId);
    List<EventTO> eventsTransfer;
    try {
      eventsTransfer = restEventFacade.findEvents(param);
    } 
    catch (UnitOfWorkException ex) {
      System.err.println(ex.getMessage());
      return;
    }
    for (EventTO eventItem : eventsTransfer) {
      Event newEvent = new Event();
      newEvent.update(eventItem);
      events.add(newEvent);
    }
  }
  
  public Event getEvent() {
    return event;
  }
  
  public void setEvent(Event event) {
    this.event = event;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
  
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}
