/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.data.sa;

public class AdapterFactory {
  
  public static final int PAYPAL = 1;
  public static final int PLACE_TO_PAY = 2;
  
  public static PaymentsProxy getPaymentAdapter(int entity) {
    PaymentsProxy proxy = null;
    switch (entity) {
      case PAYPAL:
        proxy = new PayPalAdapter();
        break;
      case PLACE_TO_PAY:
        proxy = new PlaceToPayAdapter();
        break;
    }
    return proxy;
  }
}
