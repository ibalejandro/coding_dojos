package co.edu.eafit.conferre.data.sa;

import co.edu.eafit.conferre.data.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.TransactionException;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public class PayPalAdapter implements PaymentsProxy {

  @Override
  public boolean payComission(PaymentTO payment) throws UnitOfWorkException {
    if (payment == null) throw new TransactionException("Payment not found");
    if (payment.getAmount() < 0) {
      throw new TransactionException("Amount can't be negative");
    }
    return true;
  }
}