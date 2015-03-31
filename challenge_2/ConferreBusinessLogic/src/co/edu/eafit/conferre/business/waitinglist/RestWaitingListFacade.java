package co.edu.eafit.conferre.business.waitinglist;

import co.edu.eafit.conferre.support.to.WaitingListTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public class RestWaitingListFacade implements WaitingListFacade {
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public WaitingListTO createWaitingList(WaitingListTO waitingList) throws UnitOfWorkException {
    CreateWaitingListUseCase useCase = new CreateWaitingListUseCase();
    WaitingListTO waitingListResult = null;
    try {
      waitingListResult = (WaitingListTO) useCase.execute(waitingList); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return waitingListResult;
  }
}
