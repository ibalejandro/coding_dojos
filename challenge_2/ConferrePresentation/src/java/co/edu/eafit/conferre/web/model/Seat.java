/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.support.to.SeatTO;

public class Seat implements Comparable<Seat> {
  
  private String id;
  private int number;
  private String type;
  private boolean available;
  private String spaceId;
  private String assistantId;
  
  public Seat() {
    
  }
  
  public Seat(Seat copy) {
    id = copy.getId();
    number = copy.getNumber();
    type = copy.getType();
    available = copy.isAvailable();
    spaceId = copy.getSpaceId();
    assistantId = copy.getAssistantId();
  }
  
  @Override
  public int compareTo(Seat than) {
    int num1 = this.getNumber();
    int num2 = than.getNumber();
    return num1 <= num2 ? (num1 == num2 ? 0 : -1) : 1;
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public boolean isAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }
  public String getSpaceId() {
    return spaceId;
  }
  public void setSpaceId(String spaceId) {
    this.spaceId = spaceId;
  }
  public String getAssistantId() {
    return assistantId;
  }
  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }
  public String getShowType() {
    return type.equals("VIP") ? type : "REG";
  }
  
  public void update(SeatTO seat) {
    id = seat.getId();
    number = seat.getNumber();
    type = seat.getType();
    available = seat.isAvailable();
    spaceId = seat.getSpaceId();
    assistantId = seat.getAssistantId();
  }
}
