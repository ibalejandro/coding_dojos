package co.edu.eafit.conferre.data.to;

import co.edu.eafit.conferre.data.base.TransferObject;

public class SeatTO implements TransferObject {
  
  private int id;
  private int number;
  private String type;
  private boolean available;
  private int spaceId;
  private int assistantId;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
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
  public int getSpaceId() {
    return spaceId;
  }
  public void setSpaceId(int spaceId) {
    this.spaceId = spaceId;
  }
  public int getAssistantId() {
    return assistantId;
  }
  public void setAssistantId(int assistantId) {
    this.assistantId = assistantId;
  }
}
