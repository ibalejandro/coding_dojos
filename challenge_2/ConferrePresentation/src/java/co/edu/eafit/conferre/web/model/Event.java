/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.support.to.EventTO;
import java.util.Date;

public class Event {
  
  private String id;
  private String name;
  private String type;
  private String description;
  private Date date;
  private int availableSeats;
  private String conferenceId;
  
  public Event() {
    
  }
  
  public Event(Event copy) {
    id = copy.getId();
    name = copy.getName();
    type = copy.getType();
    description = copy.getDescription();
    date = copy.getDate();
    availableSeats = copy.getAvailableSeats();
    conferenceId = copy.getConferenceId();
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public int getAvailableSeats() {
    return availableSeats;
  }
  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }
  public String getConferenceId() {
    return conferenceId;
  }
  public void setConferenceId(String conferenceId) {
    this.conferenceId = conferenceId;
  }
  
  public void update(EventTO event) {
    id = event.getId();
    name = event.getName();
    type = event.getType();
    description = event.getDescription();
    date = event.getDate();
    availableSeats = event.getAvailableSeats();
    conferenceId = event.getConferenceId();
  }
  
  public void clearFields() {
    id = "";
    name = "";
    type = "";
    description = "";
    date = null;
    availableSeats = 0;
    conferenceId = "";
  }
}
