package co.edu.eafit.conferre.business.events;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.EventDAO;
import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class FindEventUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    EventTO event = (EventTO) params;
    validateEventData(event);
    EventDAO eventDAO = FactoryDAO.createEventDAO();
    TransferObjectList resultList;
    try {
      resultList = eventDAO.retrieve(event);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return resultList;
  }
  
  private EventTO validateEventData(EventTO event) throws ValidationException {
    if (event.getId() == null)
      event.setId(GenericDAO.ANY_PATTERN);
    if (event.getName() == null)
      event.setName(GenericDAO.ANY_PATTERN);
    if (event.getType() == null)
      event.setType(GenericDAO.ANY_PATTERN);
    if (event.getDescription() == null)
      event.setDescription(GenericDAO.ANY_PATTERN);
    //if (conference.getDate() == null); //Cualquier fecha
    if (event.getAvailableSeats() <= 0)
      event.setAvailableSeats(-1);
    if (event.getConferenceId() == null)
      event.setConferenceId(GenericDAO.ANY_PATTERN);
    return event;
  }
}
