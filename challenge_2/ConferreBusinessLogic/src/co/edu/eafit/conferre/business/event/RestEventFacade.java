package co.edu.eafit.conferre.business.event;

import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public class RestEventFacade implements EventFacade {
  
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public EventTO createEvent(EventTO event) throws UnitOfWorkException {
    CreateEventUseCase useCase = new CreateEventUseCase();
    EventTO eventResult = null;
    try {
      eventResult = (EventTO) useCase.execute(event); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return eventResult;
  }
}
