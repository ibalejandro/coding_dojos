package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.data.to.RenterTO;
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
}
