package co.edu.eafit.conferre.business.seats;

import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public interface SeatFacade {
  public SeatTO createSeat(SeatTO conference) throws UnitOfWorkException;
}
