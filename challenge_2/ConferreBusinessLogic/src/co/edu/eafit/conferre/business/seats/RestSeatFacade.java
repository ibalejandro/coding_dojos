package co.edu.eafit.conferre.business.seats;

import java.util.List;

import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public class RestSeatFacade implements SeatFacade {
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public SeatTO createSeat(SeatTO seat) throws UnitOfWorkException {
    CreateSeatUseCase useCase = new CreateSeatUseCase();
    SeatTO seatResult = null;
    try {
      seatResult = (SeatTO) useCase.execute(seat); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return seatResult;
  }
  
  @Override
  //@Path("/find")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public List<SeatTO> findSeats(SeatTO params) 
      throws UnitOfWorkException {
    if (params == null) params = new SeatTO();
    FindSeatUseCase useCase = new FindSeatUseCase();
    List<SeatTO> result;
    try {
      TransferObjectList seatResult = 
          (TransferObjectList) useCase.execute(params); 
      result = (List<SeatTO>)(List<?>) seatResult.getList();
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return result;
  }
  
  @Override
  //@Path("/update")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public SeatTO reserve(SeatTO params) throws UnitOfWorkException {
    ReserveSeatUseCase useCase = new ReserveSeatUseCase();
    SeatTO result;
    try {
      result = (SeatTO) useCase.execute(params);
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return result;
  }
}
