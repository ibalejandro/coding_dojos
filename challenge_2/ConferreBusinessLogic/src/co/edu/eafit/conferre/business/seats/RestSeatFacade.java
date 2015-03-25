package co.edu.eafit.conferre.business.seats;

import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

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
}
