package co.edu.eafit.conferre.data.sa;

import co.edu.eafit.conferre.data.base.TransferObject;

public class VtigerRestAPIAdapter implements VtigerAdapter {

  @Override
  public boolean saveAssistant(TransferObject assistant) {
    // Utilizar biblioteca externa para guardar un nuevo usuario en el CRM
    return false;
  }

  @Override
  public TransferObject retrieveAssistant(TransferObject assistant) {
    // Utilizar biblioteca externa para buscar un contacto en los usuarios
    // registrados.
    return null;
  }
  
}
