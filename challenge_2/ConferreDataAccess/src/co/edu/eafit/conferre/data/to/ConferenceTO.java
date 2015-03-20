package co.edu.eafit.conferre.data.to;

import java.util.Date;

import co.edu.eafit.conferre.data.base.TransferObject;

public class ConferenceTO implements TransferObject {
  
  private String id;
  private String name;
  private String lecturerName;
  private String type;
  private Date date;
  private int availableSeats;
  private int renterId;

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
  public int getRenterId() {
    return renterId;
  }
  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }
}
