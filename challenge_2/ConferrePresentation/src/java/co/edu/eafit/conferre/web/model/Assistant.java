/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.support.to.AssistantTO;

public class Assistant {
  
  private String id;
  private String name;
  private String identification;
  private String phoneNumber;
  private String email;
  private String password;
  private boolean gender;
  
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
  
  public void update(AssistantTO assistant) {
    id = assistant.getId();
    name = assistant.getName();
    identification = assistant.getIdentification();
    phoneNumber = assistant.getPhoneNumber();
    email = assistant.getEmail();
    password = assistant.getPassword();
    gender = assistant.isMale();
  }
}
