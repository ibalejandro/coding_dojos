package co.edu.eafit.conferre.business.seats;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.SeatDAO;
import co.edu.eafit.conferre.support.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class FindSeatUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SeatTO seat = (SeatTO) params;
    validateSeatData(seat);
    SeatDAO seatDAO = DAOFactory.createSeatDAO();
    TransferObjectList resultList;
    try {
      resultList = seatDAO.retrieve(seat);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return resultList;
  }

  private SeatTO validateSeatData(SeatTO seat) throws ValidationException {
    if (seat.getId() == null)
      seat.setId(GenericDAO.ANY_PATTERN);
    if (seat.getNumber() <= 0)
      seat.setNumber(-1);
    if (seat.getType() == null)
      seat.setType(GenericDAO.ANY_PATTERN);
    if (seat.getSpaceId() == null)
      seat.setSpaceId(GenericDAO.ANY_PATTERN);
    if (seat.getAssistantId() == null)
      seat.setAssistantId(GenericDAO.ANY_PATTERN);
    return seat;
  }
}
