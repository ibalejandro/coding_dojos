/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.assistants.RestAssistantFacade;
import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Assistant;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AssistantsController {

  private Assistant assistant;
  private RestAssistantFacade restAssistantFacade;
  private FacesContext context;
  
  @PostConstruct
  public void init() {
    assistant = new Assistant();
    restAssistantFacade = new RestAssistantFacade();
  }
  
  public void login() {
    AssistantTO assistantTO = new AssistantTO(assistant.getId(), assistant.getName(),
            assistant.getIdentification(), assistant.getPhoneNumber(),
            assistant.getEmail(), assistant.getPassword(), assistant.isMale());
    try {
      System.out.println("email: " + assistantTO.getEmail());
      System.out.println("password: " + assistantTO.getPassword());
      AssistantTO assistantResult = restAssistantFacade.authenticate(assistantTO);
      if (assistantResult == null) {
        showMessage("Autenticación", "El correo electrónico o contraseña "
                + "son inválidos");
        return;
      }
      assistant.update(assistantResult);
    }
    catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      
      String message;
      if (ex instanceof ValidationException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
    try {
      context = FacesContext.getCurrentInstance();
      context.getExternalContext().redirect("conferences_index.jsf");
    }
    catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }
  }
  
  public void register() {
    AssistantTO assistantTO = new AssistantTO(assistant.getId(), assistant.getName(),
            assistant.getIdentification(), assistant.getPhoneNumber(),
            assistant.getEmail(), assistant.getPassword(), assistant.isMale());
    try {
      AssistantTO assistantResult = restAssistantFacade.createAssistant(assistantTO);
      assistant.update(assistantResult);
    }
    catch (UnitOfWorkException ex) {
      System.err.println("Error: " + ex.getMessage());
      
      String message;
      if (ex instanceof ValidationException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
    try {
      context = FacesContext.getCurrentInstance();
      context.getExternalContext().redirect("conferences_index.jsf");
    }
    catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }
  }

  public Assistant getAssistant() {
    return assistant;
  }

  public void setAssistant(Assistant assistant) {
    this.assistant = assistant;
  }
  
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}
