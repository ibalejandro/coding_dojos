package co.edu.eafit.conferre.data.sa;

import co.edu.eafit.conferre.support.base.TransferObject;

public interface CRMProxy {
  
  public boolean saveAssistant(TransferObject assistant);
  public TransferObject retrieveAssistant(TransferObject assistant);
}
