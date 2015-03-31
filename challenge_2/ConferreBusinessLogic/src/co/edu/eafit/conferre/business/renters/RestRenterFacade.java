package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.support.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public class RestRenterFacade implements RenterFacade {
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public RenterTO createRenter(RenterTO renter) throws UnitOfWorkException {
    CreateRenterUseCase useCase = new CreateRenterUseCase();
    RenterTO renterResult = null;
    try {
      renterResult = (RenterTO) useCase.execute(renter); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return renterResult;
  }

  @Override
  //@Path("/login")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public RenterTO authenticate(RenterTO renter) throws UnitOfWorkException {
    LoginRenterUseCase useCase = new LoginRenterUseCase();
    RenterTO renterResult;
    try {
      renterResult = (RenterTO) useCase.execute(renter);
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return renterResult;
  }
}
