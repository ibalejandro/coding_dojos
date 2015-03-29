package co.edu.eafit.conferre.data.to;

import java.util.Date;

import co.edu.eafit.conferre.data.base.TransferObject;

public class EventTO implements TransferObject {
  
  private String id;
  private String name;
  private String type;
  private String description;
  private Date date;
  private int availableSeats;
  private String conferenceId;

  public EventTO() {
    
  }
  
  public EventTO(String id, String name, String type, String description,
                 Date date, int availableSeats, String conferenceId) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.description = description;
    this.date = date;
    this.availableSeats = availableSeats;
    this.conferenceId = conferenceId;
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
}
