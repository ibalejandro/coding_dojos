package co.edu.eafit.conferre.support.to;

import co.edu.eafit.conferre.support.base.TransferObject;

public class AssistantTO implements TransferObject {
  
  private String id;
  private String name;
  private String identification;
  private String phoneNumber;
  private String email;
  private String password;
  private boolean gender;
  
  public AssistantTO() {
    
  }

  public AssistantTO(String id, String name, String identification,
          String phoneNumber, String email, String password, boolean gender) {
    this.id = id;
    this.name = name;
    this.identification = identification;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.gender = gender;
  }
  
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
  public String getIdentification() {
    return identification;
  }
  public void setIdentification(String identification) {
    this.identification = identification;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public boolean isMale() {
    return gender;
  }
  public void setMale(boolean gender) {
    this.gender = gender;
  }
}
