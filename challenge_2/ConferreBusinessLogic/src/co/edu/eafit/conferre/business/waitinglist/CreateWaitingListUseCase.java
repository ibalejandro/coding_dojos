package co.edu.eafit.conferre.business.waitinglist;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.data.dao.WaitingListDAO;
import co.edu.eafit.conferre.support.to.WaitingListTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateWaitingListUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    WaitingListTO waitingList = (WaitingListTO) params;
    validateWaitingListData(waitingList);
    WaitingListDAO waitingListDAO = DAOFactory.createWaitingListDAO();
    WaitingListTO result;
    try {
      result = (WaitingListTO) waitingListDAO.create(waitingList);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private void validateWaitingListData(WaitingListTO waitingList) throws ValidationException {
    if (waitingList.getConferenceId() == null) {
      throw new ValidationException("Seat number can't be negative");
    }
    if (waitingList.getAssistantId() == null) {
      throw new ValidationException("Assistant ID can't be negative");
    }
  }
}
