package co.edu.eafit.conferre.data.to;

import co.edu.eafit.conferre.data.base.TransferObject;

public class SeatTO implements TransferObject {
  
  private String id;
  private int number;
  private String type;
  private boolean available;
  private String spaceId;
  private String assistantId;
  
  public SeatTO() {
    
  }

  public SeatTO(String id, int number, String type, boolean available,
                String spaceId, String assistantId) {
    this.id = id;
    this.number = number;
    this.type = type;
    this.available = available;
    this.spaceId = spaceId;
    this.assistantId = assistantId;
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
}
