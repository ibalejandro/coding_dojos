/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.conferences.RestConferenceFacade;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.web.model.Conference;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class ConferencesController {
  private Conference conference;
  private RestConferenceFacade restConferenceFacade;
  
  @PostConstruct
  public void init() {
    conference = new Conference();
    conference.setRenterId("3b105dc6-46f9-441a-8995-18c26db9046b");
  }
  
  public void click() {
    restConferenceFacade = new RestConferenceFacade();
    
    ConferenceTO newConference = new ConferenceTO(
            conference.getId(), conference.getName(),
            conference.getLecturerName(), conference.getType(),
            conference.getDate(), conference.getAvailableSeats(),
            conference.getRenterId());

    ConferenceTO result;
    try {
      result = restConferenceFacade.createConference(newConference);
    } catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      ex.printStackTrace();
      return;
    }
    conference.update(result);

    RequestContext requestContext = RequestContext.getCurrentInstance();
    requestContext.update("form:display");
    requestContext.execute("PF('dlg').show()");
  }
  public Conference getConference() {
    return conference;
  }
  public void setConference(Conference conference) {
    this.conference = conference;
  }
}
