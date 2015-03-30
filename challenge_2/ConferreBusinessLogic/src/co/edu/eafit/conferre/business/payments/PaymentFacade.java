/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.payments;

import co.edu.eafit.conferre.data.to.PaymentTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface PaymentFacade {
  public PaymentTO makePayment(PaymentTO renter) throws UnitOfWorkException;
}
