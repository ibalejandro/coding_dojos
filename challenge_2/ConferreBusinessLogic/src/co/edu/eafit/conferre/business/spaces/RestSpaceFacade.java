package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public class RestSpaceFacade implements SpaceFacade {
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public SpaceTO createSpace(SpaceTO space) throws UnitOfWorkException {
    CreateSpaceUseCase useCase = new CreateSpaceUseCase();
    SpaceTO spaceResult = null;
    try {
      spaceResult = (SpaceTO) useCase.execute(space); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return spaceResult;
  }
}
