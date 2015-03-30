/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.payments;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.sa.AdapterFactory;
import co.edu.eafit.conferre.data.sa.PaymentsProxy;
import co.edu.eafit.conferre.data.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class PaySeatsUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    PaymentTO payment = (PaymentTO) params;
    validatePayment(payment);
    PaymentsProxy proxy = AdapterFactory
            .getPaymentAdapter(payment.getPaymentEntity());
    try {
      if (proxy.payComission(payment)) payment.setSuccess(Boolean.TRUE);
      else payment.setSuccess(Boolean.FALSE);
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return payment;
  }

  private void validatePayment(PaymentTO payment) throws ValidationException {
    if (payment == null)
      throw new ValidationException("Payment can't be blank");
    if (payment.getAmount() < 0)
      throw new ValidationException("Payment amount can't be negative");
  }
}
