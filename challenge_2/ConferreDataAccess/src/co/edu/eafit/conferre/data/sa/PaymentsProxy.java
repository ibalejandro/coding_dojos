package co.edu.eafit.conferre.data.sa;

import co.edu.eafit.conferre.support.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface PaymentsProxy {
  
  public boolean payComission(PaymentTO payment) throws UnitOfWorkException;
}
