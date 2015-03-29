package co.edu.eafit.conferre.business.base;

import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface UnitOfWork {
  
  public TransferObject execute(TransferObject params) 
      throws UnitOfWorkException;
}
