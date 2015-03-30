/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.events;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.SpaceDAO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;
import java.util.List;

public class AssignSpaceUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SpaceTO space = (SpaceTO) params;
    String eventId = space.getEventId();
    space = validateSpaceData(space);
    SpaceDAO spaceDAO = DAOFactory.createSpaceDAO(DAOFactory.SOURCE_DB);
    SpaceTO result;
    try {
      TransferObjectList foundSpaces = spaceDAO.retrieve(space);
      result = (SpaceTO) foundSpaces.get(0);
      result.setAvailable(true);
      result.setEventId(eventId);
      if (spaceDAO.update(result) == 0) result = null;
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private SpaceTO validateSpaceData(SpaceTO space) throws ValidationException {
    if (space.getId() == null || space.getId().equals("")) {
      throw new ValidationException("Space can't be blank");
    }
    if (space.getEventId()== null || space.getEventId().equals("")) {
      throw new ValidationException("Event can't be blank");
    }
    space.setEventId(GenericDAO.ANY_PATTERN);
    space.setAvailable(true);
    if (space.getMaxCapacity() <= 0)
      space.setMaxCapacity(-1);
    if (space.getLocation() == null || space.getLocation().isEmpty())
      space.setLocation(GenericDAO.ANY_PATTERN);
    return space;
  }
}
