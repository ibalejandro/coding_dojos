package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface RenterFacade {
  public RenterTO createRenter(RenterTO renter) throws UnitOfWorkException;
}
