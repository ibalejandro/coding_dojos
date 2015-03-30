/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.payments;

import co.edu.eafit.conferre.data.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public class RestPaymentFacade implements PaymentFacade {

  @Override
  //@Path("/pay")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public PaymentTO makePayment(PaymentTO payment) throws UnitOfWorkException {
    PaySeatsUseCase useCase = new PaySeatsUseCase();
    PaymentTO paymentResult = null;
    try {
      paymentResult = (PaymentTO) useCase.execute(payment); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return paymentResult;
  }
}
