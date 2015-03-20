package co.edu.eafit.conferre.data.to;

import co.edu.eafit.conferre.data.base.TransferObject;

public class SpaceTO implements TransferObject {
  
  private int id;
  private int maxCapacity;
  private String location;
  private boolean available;
  private int eventId;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getSeatsCapacity() {
    return maxCapacity;
  }
  public void setSeatsCapacity(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public int getMaxCapacity() {
    return maxCapacity;
  }
  public void setMaxCapacity(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }
  public boolean isAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }
  public int getEventId() {
    return eventId;
  }
  public void setEventId(int eventId) {
    this.eventId = eventId;
  }
}
