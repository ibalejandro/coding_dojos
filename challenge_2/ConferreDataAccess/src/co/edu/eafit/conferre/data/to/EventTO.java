package co.edu.eafit.conferre.data.to;

import java.util.Date;

import co.edu.eafit.conferre.data.base.TransferObject;

public class EventTO implements TransferObject {
  
  private int id;
  private String name;
  private String type;
  private String description;
  private Date date;
  private int availableSeats;
  private int conferenceId;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
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
  public int getConferenceId() {
    return conferenceId;
  }
  public void setConferenceId(int conferenceId) {
    this.conferenceId = conferenceId;
  }
}
