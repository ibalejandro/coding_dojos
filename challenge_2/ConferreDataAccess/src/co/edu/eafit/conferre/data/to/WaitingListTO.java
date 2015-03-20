package co.edu.eafit.conferre.data.to;

import java.util.List;

public class WaitingListTO {
  
  private int id;
  private int conferenceId;
  private List<Integer> assistantsIds;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getConferenceId() {
    return conferenceId;
  }
  public void setConferenceId(int conferenceId) {
    this.conferenceId = conferenceId;
  }
  public List<Integer> getAssistantsIds() {
    return assistantsIds;
  }
  public void setAssistantsIds(List<Integer> assistantsIds) {
    this.assistantsIds = assistantsIds;
  }
}
