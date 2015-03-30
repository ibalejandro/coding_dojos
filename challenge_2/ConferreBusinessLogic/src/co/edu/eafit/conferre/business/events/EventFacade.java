package co.edu.eafit.conferre.business.events;

import java.util.List;

import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface EventFacade {
  public EventTO createEvent(EventTO event) throws UnitOfWorkException;
  public List<EventTO> findEvents(EventTO event) throws UnitOfWorkException;
  public int associateSpace(SpaceTO space) throws UnitOfWorkException;
}
