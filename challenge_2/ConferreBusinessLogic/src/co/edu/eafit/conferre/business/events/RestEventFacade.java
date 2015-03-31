package co.edu.eafit.conferre.business.events;

import java.util.List;

import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.support.to.EventTO;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

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
  
  @Override
  //@Path("/find")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public List<EventTO> findEvents(EventTO params) 
      throws UnitOfWorkException {
    if (params == null) params = new EventTO();
    FindEventUseCase useCase = new FindEventUseCase();
    List<EventTO> result;
    try {
      TransferObjectList eventsResult = 
          (TransferObjectList) useCase.execute(params); 
      result = (List<EventTO>)(List<?>) eventsResult.getList();
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return result;
  }
  
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public int associateSpace(SpaceTO space) throws UnitOfWorkException {
    AssignSpaceUseCase useCase = new AssignSpaceUseCase();
    SpaceTO spaceResult = null;
    try {
      spaceResult = (SpaceTO) useCase.execute(space); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return spaceResult == null ? 0 : 1;
  }
}
