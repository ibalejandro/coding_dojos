/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.data.to.ConferenceTO;
import java.util.Date;

public class Conference {
  private String id;
  private String name;
  private String lecturerName;
  private String type;
  private Date date;
  private int availableSeats;
  private String renterId;

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
  public String getLecturerName() {
    return lecturerName;
  }
  public void setLecturerName(String lecturerName) {
    this.lecturerName = lecturerName;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
  public String getRenterId() {
    return renterId;
  }
  public void setRenterId(String renterId) {
    this.renterId = renterId;
  }
  
  public void update(ConferenceTO conference) {
    id = conference.getId();
    name = conference.getName();
    lecturerName = conference.getLecturerName();
    type = conference.getType();
    date = conference.getDate();
    availableSeats = conference.getAvailableSeats();
    renterId = conference.getRenterId();
  }
}
