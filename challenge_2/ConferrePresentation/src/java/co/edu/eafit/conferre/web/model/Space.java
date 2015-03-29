/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.data.to.SpaceTO;

/**
 *
 * @author svanegas
 */
public class Space {
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
  public int getMaxCapacity() {
    return maxCapacity;
  }
  public void setMaxCapacity(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
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
  
  public void update(SpaceTO space) {
    id = space.getId();
    maxCapacity = space.getMaxCapacity();
    location = space.getLocation();
    available = space.isAvailable();
    eventId = space.getEventId();
  }
}
