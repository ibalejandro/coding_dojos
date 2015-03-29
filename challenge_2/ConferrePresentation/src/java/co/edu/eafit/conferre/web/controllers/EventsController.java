/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.web.model.Event;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EventsController {
  
  private Event event;

  @PostConstruct
  public void init() {
    event = new Event();
  }
  
  public Event getEvent() {
    return event;
  }
  
  public void setEvent(Event event) {
    this.event = event;
  }
}
