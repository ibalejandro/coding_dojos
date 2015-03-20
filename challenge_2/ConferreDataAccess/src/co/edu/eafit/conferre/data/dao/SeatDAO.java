package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.SeatTO;

public class SeatDAO implements GenericDAO {

  Connection conn;
  
  public SeatDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    SeatTO seat = null;
    try {
      seat = (SeatTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO seats VALUES(?, ?, ?)");
      prep.setInt(1, seat.getNumber());
      prep.setString(2, seat.getType());
      prep.setBoolean(3, seat.isAvailable());
      prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
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
                          + "AND type = ? AND available = ?");
      prep.setInt(1, seat.getNumber());
      prep.setString(2, seat.getType());
      prep.setBoolean(3, seat.isAvailable());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        SeatTO row = new SeatTO();
        row.setNumber(resultSet.getInt("number"));
        row.setType(resultSet.getString("type"));
        row.setAvailable(resultSet.getBoolean("available"));
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
              + "type = ?, available = ? WHERE id = ?");
      prep.setInt(1, seat.getNumber());
      prep.setString(2, seat.getType());
      prep.setBoolean(3, seat.isAvailable());
      prep.setInt(4,  seat.getId());
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
      prep.setInt(1, seat.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
