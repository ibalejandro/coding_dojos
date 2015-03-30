/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.renters.RestRenterFacade;
import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Renter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class RentersController {
  private Renter renter;
  private Renter loggedRenter;
  private RestRenterFacade restRenterFacade;
  private FacesContext context;
  
  @PostConstruct
  public void init() {
    renter = new Renter();
    restRenterFacade = new RestRenterFacade();
  }
  
  public void login() {
    RenterTO renterTO = new RenterTO(renter.getId(), renter.getName(),
            renter.getIdentification(), renter.getPhoneNumber(),
            renter.getEmail(), renter.getPassword(), renter.isMale());
    try {
      System.out.println("email: " + renterTO.getEmail());
      System.out.println("password: " + renterTO.getPassword());
      RenterTO renterResult = restRenterFacade.authenticate(renterTO);
      if (renterResult == null) {
        showMessage("Autenticación", "El correo electrónico o contraseña "
                + "son inválidos");
        return;
      }
      renter.update(renterResult);
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
      context.getExternalContext().redirect("create_conference.jsf");
    }
    catch (IOException ex) {
      showMessage("Error", "An error has ocurred");
    }
  }

  public Renter getRenter() {
    return renter;
  }

  public void setRenter(Renter renter) {
    this.renter = renter;
  }
  
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}