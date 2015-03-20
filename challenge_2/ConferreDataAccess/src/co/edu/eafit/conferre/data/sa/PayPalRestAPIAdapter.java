package co.edu.eafit.conferre.data.sa;

public class PayPalRestAPIAdapter implements PayPalAdapter {

  @Override
  public boolean payComission(double amount) {
    // Se utiliza biblioteca externa de PayPal para realizar el pago de la
    // comisión.
    return true;
  }
  
}
