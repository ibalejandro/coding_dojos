package co.edu.eafit.conferre.data.to;

import co.edu.eafit.conferre.data.base.TransferObject;

public class WaitingListTO implements TransferObject {
  
  private String id;
  private String conferenceId;
  private String assistantId;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getConferenceId() {
    return conferenceId;
  }
  public void setConferenceId(String conferenceId) {
    this.conferenceId = conferenceId;
  }
  public String getAssistantId() {
    return assistantId;
  }
  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }
}
