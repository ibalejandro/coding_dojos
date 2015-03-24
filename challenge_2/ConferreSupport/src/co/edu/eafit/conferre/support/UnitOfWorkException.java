package co.edu.eafit.conferre.support;

public class UnitOfWorkException extends Exception {

  public UnitOfWorkException(Exception e) {
    super(e);
  }
  
  public UnitOfWorkException(String exception) {
    super(exception);
  }
}
