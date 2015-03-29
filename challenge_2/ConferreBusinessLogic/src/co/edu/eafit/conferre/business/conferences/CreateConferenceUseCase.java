package co.edu.eafit.conferre.business.conferences;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.ConferenceDAO;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;
import co.edu.eafit.conferre.support.ValidationException;

public class CreateConferenceUseCase implements UnitOfWork {

  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    ConferenceTO conference = (ConferenceTO) params;
    validateConferenceData(conference);
    ConferenceDAO conferenceDAO = FactoryDAO.createConferenceDAO();
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
  }
}