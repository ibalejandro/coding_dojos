package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.util.List;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;

public class WaitingListDAO implements GenericDAO {

  Connection conn;
  
  public WaitingListDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    return null;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    return null;
  }

  @Override
  public int update(TransferObject object) {
    return 0;
  }

  @Override
  public int delete(TransferObject params) {
    return 0;
  }

}
