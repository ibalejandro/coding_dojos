package co.edu.eafit.conferre.business.events;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.EventDAO;
import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateEventUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    EventTO event = (EventTO) params;
    validateEventData(event);
    EventDAO eventDAO = DAOFactory.createEventDAO();
    EventTO result;
    try {
      result = (EventTO) eventDAO.create(event);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private void validateEventData(EventTO event) throws ValidationException {
    if (event.getName() == null || event.getName().equals("")) {
      throw new ValidationException("Event name can't be blank");
    }
  }
}
