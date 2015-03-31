package co.edu.eafit.conferre.data.base;

import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.support.base.TransferObjectList;

public interface GenericDAO {
  
  public static final String ANY_PATTERN = "%";
  
  public TransferObject create(TransferObject newObject) throws Exception;
  public TransferObjectList retrieve(TransferObject params);
  public int update(TransferObject object);
  public int delete(TransferObject params);
}
