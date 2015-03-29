package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.to.WaitingListTO;

public class WaitingListDAO implements GenericDAO {

  Connection conn;
  
  public WaitingListDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) throws Exception {
    WaitingListTO waitingList = null;
    try {
      waitingList = (WaitingListTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO waiting_list VALUES(?, ?, ?)");
      prep.setString(2, waitingList.getConferenceId());
      prep.setString(3, waitingList.getAssistantId());
      do {
        UUID id = UUID.randomUUID();
        waitingList.setId(id.toString());
        prep.setString(1, waitingList.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      throw new Exception(e);
    }
    return waitingList;
  }

  @Override
  public TransferObjectList retrieve(TransferObject params) {
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
