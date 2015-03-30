package co.edu.eafit.conferre.business.conferences;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.ConferenceDAO;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class FindConferenceUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    ConferenceTO conference = (ConferenceTO) params;
    validateConferenceData(conference);
    ConferenceDAO conferenceDAO = DAOFactory.createConferenceDAO();
    TransferObjectList resultList;
    try {
      resultList = conferenceDAO.retrieve(conference);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return resultList;
  }

  private ConferenceTO validateConferenceData(ConferenceTO conference) throws ValidationException {
    if (conference.getId() == null)
      conference.setId(GenericDAO.ANY_PATTERN);
    if (conference.getName() == null)
      conference.setName(GenericDAO.ANY_PATTERN);
    if (conference.getLecturerName() == null)
      conference.setLecturerName(GenericDAO.ANY_PATTERN);
    if (conference.getType() == null)
      conference.setType(GenericDAO.ANY_PATTERN);
    //if (conference.getDate() == null); //Cualquier fecha
    if (conference.getAvailableSeats() <= 0)
      conference.setAvailableSeats(-1);
    if (conference.getRenterId() == null)
      conference.setRenterId(GenericDAO.ANY_PATTERN);
    return conference;
  }
}
