package co.edu.eafit.conferre.conferences.business;

import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public interface UnitOfWork {
  
  public TransferObject execute(TransferObject params) 
      throws UnitOfWorkException;

}
