/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;

import co.edu.eafit.conferre.business.payments.RestPaymentFacade;
import co.edu.eafit.conferre.support.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.TransactionException;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import co.edu.eafit.conferre.web.model.Payment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "paymentsController")
@SessionScoped
public class PaymentsController {
  
  private Payment payment;
  private RestPaymentFacade restPaymentFacade;
  private FacesContext context;
  
  @ManagedProperty(value="#{seatsController}")
  private SeatsController seatsController;
  
  @PostConstruct
  public void init() {
    payment = new Payment();
    restPaymentFacade = new RestPaymentFacade();
  }
  
  public void confirmPayment(double amountToPay) {
    
    if (seatsController.getSeatsToBeBought().isEmpty()) {
      showMessage("Comprar sillas", "Por favor selecciona al menos una silla");
      return;
    }
    //Cambiar el estado de las sillas
    seatsController.updateAndSaveSeatsState(false);
    
    //Procesar el pago
    payment.setAmount(amountToPay);
    PaymentTO paymentTransfer = new PaymentTO(payment.getAmount(),
            payment.getPaymentEntity(), payment.getExtraInformation(),
            payment.getSuccess());
    PaymentTO result;
    try {
      result = restPaymentFacade.makePayment(paymentTransfer);
    }
    catch (UnitOfWorkException ex) {
      //Revertir el cambio en las sillas
      seatsController.updateAndSaveSeatsState(true);
      
      System.err.println("Error: " + ex.getMessage());
      ex.printStackTrace();
      
      String message;
      if (ex instanceof ValidationException || 
          ex instanceof TransactionException) message = ex.getMessage();
      else message = "An error has occurred";
      showMessage("Error", message);
      return;
    }
    Payment response = new Payment();
    response.update(result);
    if (response.getSuccess() != null &&
        response.getSuccess().equals(Boolean.TRUE)) {
      context = FacesContext.getCurrentInstance();
      showMessage("Éxito", "Las sillas se han reservado satisfactoriamente");
      seatsController.init();
    }
    else {
      seatsController.updateAndSaveSeatsState(true);
      showMessage("Error", "Un error ha ocurrido y no se han podido reservar"
              + " las sillas");
    }
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public SeatsController getSeatsController() {
    return seatsController;
  }

  public void setSeatsController(SeatsController seatsController) {
    this.seatsController = seatsController;
  }
  
  private void showMessage(String title, String content) {
    context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(title,  content));
  }
}
