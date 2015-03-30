/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.support.exceptions;

public class TransactionException extends UnitOfWorkException {
  
  public TransactionException(String exception) {
    super(exception);
  }
}
