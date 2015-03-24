package co.edu.eafit.conferre.business.event;

import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public interface EventFacade {
  public EventTO createEvent(EventTO event) throws UnitOfWorkException;
}
