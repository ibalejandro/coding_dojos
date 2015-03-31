package co.edu.eafit.conferre.support.to;

import co.edu.eafit.conferre.support.base.TransferObject;

public class SpaceTO implements TransferObject {
  
  private String id;
  private int maxCapacity;
  private String location;
  private boolean available;
  private String eventId;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
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
  public String getEventId() {
    return eventId;
  }
  public void setEventId(String eventId) {
    this.eventId = eventId;
  }
}
