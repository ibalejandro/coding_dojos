package co.edu.eafit.conferre.business.seats;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.SeatDAO;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateSeatUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SeatTO seat = (SeatTO) params;
    validateSeatData(seat);
    SeatDAO seatDAO = FactoryDAO.createSeatDAO();
    SeatTO result;
    try {
      result = (SeatTO) seatDAO.create(seat);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private void validateSeatData(SeatTO seat) throws ValidationException {
    if (seat.getNumber() < 0) {
      throw new ValidationException("Seat number can't be negative");
    }
  }
}
