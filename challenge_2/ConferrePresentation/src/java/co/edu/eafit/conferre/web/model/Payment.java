/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.model;

import co.edu.eafit.conferre.support.to.PaymentTO;

public class Payment {
  private double amount;
  private int paymentEntity;
  private String [] extraInformation;
  private Boolean success;
  
  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public int getPaymentEntity() {
    return paymentEntity;
  }

  public void setPaymentEntity(int paymentEntity) {
    this.paymentEntity = paymentEntity;
  }

  public String[] getExtraInformation() {
    return extraInformation;
  }

  public void setExtraInformation(String[] extraInformation) {
    this.extraInformation = extraInformation;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public void update(PaymentTO payment) {
    amount = payment.getAmount();
    paymentEntity = payment.getPaymentEntity();
    extraInformation = payment.getExtraInformation();
    success = payment.getSuccess();
  }
}
