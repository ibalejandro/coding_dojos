/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.data.to;

import co.edu.eafit.conferre.data.base.TransferObject;

public class PaymentTO implements TransferObject {
  private double amount;
  private int paymentEntity;
  private String [] extraInformation;
  private Boolean success;

  public PaymentTO() {
    
  }
  
  public PaymentTO(double amount, int paymentEntity, String[] extraInformation, Boolean success) {
    this.amount = amount;
    this.paymentEntity = paymentEntity;
    this.extraInformation = extraInformation;
    this.success = success;
  }
  
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
}
