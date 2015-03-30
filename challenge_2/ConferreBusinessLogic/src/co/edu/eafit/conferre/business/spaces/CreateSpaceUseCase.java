package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.SpaceDAO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateSpaceUseCase implements UnitOfWork {
  
  
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SpaceTO space = (SpaceTO) params;
    validateSpaceData(space);
    SpaceDAO spaceDAO = DAOFactory.createSpaceDAO(DAOFactory.SOURCE_DB);
    SpaceTO result;
    try {
      result = (SpaceTO) spaceDAO.create(space);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }
  
  private void validateSpaceData(SpaceTO space) throws ValidationException {
    if (space.getLocation() == null || space.getLocation().equals("")) {
      throw new ValidationException("Space location can't be blank");
    }
  }
}
