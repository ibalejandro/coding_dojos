package co.edu.eafit.conferre.support.to;

import java.util.Date;

import co.edu.eafit.conferre.support.base.TransferObject;

public class ConferenceTO implements TransferObject {
  
  private String id;
  private String name;
  private String lecturerName;
  private String type;
  private Date date;
  private int availableSeats;
  private String renterId;

  public ConferenceTO() {
    
  }

  public ConferenceTO(String id, String name, String lecturerName, String type,
                      Date date, int availableSeats, String renterId) {
    this.id = id;
    this.name = name;
    this.lecturerName = lecturerName;
    this.type = type;
    this.date = date;
    this.availableSeats = availableSeats;
    this.renterId = renterId;
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
}
