package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.SpaceDAO;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class FindSpacesUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SpaceDAO spaceDAO = DAOFactory.createSpaceDAO(2);
    TransferObjectList resultList;
    SpaceTO space = validateSpaceParam();
    try {
      resultList = spaceDAO.retrieve(space);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return resultList;
  }
  
  private SpaceTO validateSpaceParam() throws ValidationException {
    SpaceTO space = new SpaceTO();
    space.setAvailable(true);
    space.setId(GenericDAO.ANY_PATTERN);
    space.setLocation(GenericDAO.ANY_PATTERN);
    space.setMaxCapacity(-1);
    space.setEventId(GenericDAO.ANY_PATTERN);
    space.setSeatsCapacity(-1);
    return space;
  }
}
