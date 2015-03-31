package co.edu.eafit.conferre.data.sa;

import co.edu.eafit.conferre.support.base.TransferObject;

public class ConsultedRestAPIAdapter implements ConsultedAdapter {

  @Override
  public boolean shareConference(TransferObject conference) {
    // Acá se cogería la información necesaria de la conferencia, como el título
    // la fecha y luego se utilizaría una biblioteca de Consulted para publicar.
    return true;
  }

}
