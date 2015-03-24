package co.edu.eafit.conferre.data.to;

import java.util.List;

public class WaitingListTO {
  
  private String id;
  private String conferenceId;
  private List<String> assistantsIds;
  
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
  public List<String> getAssistantsIds() {
    return assistantsIds;
  }
  public void setAssistantsIds(List<String> assistantsIds) {
    this.assistantsIds = assistantsIds;
  }
}
