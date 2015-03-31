package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.support.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface RenterFacade {
  public RenterTO createRenter(RenterTO renter) throws UnitOfWorkException;
  public RenterTO authenticate(RenterTO renter) throws UnitOfWorkException;
}
