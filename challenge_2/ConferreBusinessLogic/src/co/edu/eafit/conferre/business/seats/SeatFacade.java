package co.edu.eafit.conferre.business.seats;

import java.util.List;

import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface SeatFacade {
  public SeatTO createSeat(SeatTO seat) throws UnitOfWorkException;
  public List<SeatTO> findSeats(SeatTO seat) throws UnitOfWorkException;
}
