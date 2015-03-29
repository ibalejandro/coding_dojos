package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.SpaceDAO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;
import co.edu.eafit.conferre.support.ValidationException;

public class CreateSpaceUseCase implements UnitOfWork {
  
  private static final int SOURCE_TEXT = 1;
  private static final int SOURCE_DB = 2;
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SpaceTO space = (SpaceTO) params;
    validateSpaceData(space);
    SpaceDAO spaceDAO = FactoryDAO.createSpaceDAO(SOURCE_DB);
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