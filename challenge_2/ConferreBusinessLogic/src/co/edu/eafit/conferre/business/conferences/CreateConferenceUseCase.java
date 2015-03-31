package co.edu.eafit.conferre.business.conferences;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.data.dao.ConferenceDAO;
import co.edu.eafit.conferre.support.to.ConferenceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateConferenceUseCase implements UnitOfWork {

  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    ConferenceTO conference = (ConferenceTO) params;
    validateConferenceData(conference);
    ConferenceDAO conferenceDAO = DAOFactory.createConferenceDAO();
    ConferenceTO result;
    try {
      result = (ConferenceTO) conferenceDAO.create(conference);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private void validateConferenceData(ConferenceTO conference) throws ValidationException {
    if (conference.getName() == null || conference.getName().equals("")) {
      throw new ValidationException("Conference name can't be blank");
    }
    if (conference.getDate() == null) {
      throw new ValidationException(("Date can't be blank"));
    }
  }
}
