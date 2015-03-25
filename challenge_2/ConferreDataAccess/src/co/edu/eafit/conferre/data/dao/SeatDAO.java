package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.SeatTO;

public class SeatDAO implements GenericDAO {

  Connection conn;
  
  public SeatDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) throws Exception {
    SeatTO seat = null;
    try {
      seat = (SeatTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO seats VALUES(?, ?, ?, ?, ?, ?)");
      prep.setInt(2, seat.getNumber());
      prep.setString(3, seat.getType());
      prep.setBoolean(4, seat.isAvailable());
      prep.setString(5, seat.getSpaceId());
      prep.setString(6, seat.getAssistantId());
      do {
        UUID id = UUID.randomUUID();
        seat.setId(id.toString());
        prep.setString(1, seat.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      throw new Exception(e);
    }
    return seat;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    SeatTO seat = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      seat = (SeatTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM seats WHERE number = ?"
                          + "AND type = ? AND available = ? AND space_id = ?"
                          + "AND assistant_id = ?");
      prep.setInt(1, seat.getNumber());
      prep.setString(2, seat.getType());
      prep.setBoolean(3, seat.isAvailable());
      prep.setString(4, seat.getSpaceId());
      prep.setString(5, seat.getAssistantId());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        SeatTO row = new SeatTO();
        row.setNumber(resultSet.getInt("number"));
        row.setType(resultSet.getString("type"));
        row.setAvailable(resultSet.getBoolean("available"));
        row.setSpaceId(resultSet.getString("space_id"));
        row.setAssistantId(resultSet.getString("assistant_id"));
        result.add(row);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public int update(TransferObject object) {
    SeatTO seat = null;
    int response = 0;
    try {
      seat = (SeatTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE seats SET number = ?, "
              + "type = ?, available = ?, space_id = ?, assistant_id = ? "
              + "WHERE id = ?");
      prep.setInt(1, seat.getNumber());
      prep.setString(2, seat.getType());
      prep.setBoolean(3, seat.isAvailable());
      prep.setString(4, seat.getSpaceId());
      prep.setString(5, seat.getAssistantId());
      prep.setString(6,  seat.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    SeatTO seat = null;
    int response = 0;
    try {
      seat = (SeatTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM seats WHERE id = ?");
      prep.setString(1, seat.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
