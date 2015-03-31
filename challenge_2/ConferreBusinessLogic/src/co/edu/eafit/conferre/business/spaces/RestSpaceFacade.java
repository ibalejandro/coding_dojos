package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import java.util.List;

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
  
  @Override
  //@Path("/emptySpaces")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public List<SpaceTO> findEmptySpaces() throws UnitOfWorkException {
    FindSpacesUseCase useCase = new FindSpacesUseCase();
    List<SpaceTO> result;
    try {
      TransferObjectList conferencesResult = 
          (TransferObjectList) useCase.execute(null); 
      result = (List<SpaceTO>)(List<?>) conferencesResult.getList();
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return result;
  }
}
