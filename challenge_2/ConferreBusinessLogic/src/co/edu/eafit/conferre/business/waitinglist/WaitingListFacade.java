package co.edu.eafit.conferre.business.waitinglist;

import co.edu.eafit.conferre.data.to.WaitingListTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public interface WaitingListFacade {
  public WaitingListTO createWaitingList(WaitingListTO waitingList) throws UnitOfWorkException;
}
