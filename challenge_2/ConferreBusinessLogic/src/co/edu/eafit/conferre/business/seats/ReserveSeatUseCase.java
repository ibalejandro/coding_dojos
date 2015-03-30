/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.seats;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.SeatDAO;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class ReserveSeatUseCase implements UnitOfWork{

  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    SeatTO seat = (SeatTO) params;
    validateSeatData(seat);
    SeatDAO seatDAO = DAOFactory.createSeatDAO();
    try {
      if (seatDAO.update(seat) == 0) seat = null;
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return seat;
  }

  private void validateSeatData(SeatTO seat) throws ValidationException {
    if (seat.getId() == null || seat.getId().isEmpty())
      throw new ValidationException("Seat can't be blank");
    if (seat.isAvailable() == false) {
      if ((seat.getAssistantId() == null || seat.getAssistantId().isEmpty())) {
        throw new ValidationException("Assistant can't be blank");
      }
    }   
  }
  
}
