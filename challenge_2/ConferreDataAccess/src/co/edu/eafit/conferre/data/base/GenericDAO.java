package co.edu.eafit.conferre.data.base;

import java.util.List;

public interface GenericDAO {
  
  public TransferObject create(TransferObject newObject);
  public List<TransferObject> retrieve(TransferObject params);
  public int update(TransferObject object);
  public int delete(TransferObject params);
}
