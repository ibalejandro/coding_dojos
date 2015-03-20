package co.edu.eafit.conferre.data.sa;

public class PlaceToPayRestAPIAdapter implements PlaceToPayAdapter {

  @Override
  public boolean payComission(double amount) {
    // Se utiliza biblioteca externa de PlaceToPay para realizar el pago de la
    // comisión.
    return true;
  }

}
