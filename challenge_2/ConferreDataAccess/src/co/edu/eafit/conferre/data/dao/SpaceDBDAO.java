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
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.to.SpaceTO;

public class SpaceDBDAO implements SpaceDAO {
  
  private Connection conn;
  
  public SpaceDBDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) throws Exception {
    SpaceTO space = null;
    try {
      space = (SpaceTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO spaces VALUES(?, ?, ?, ?, ?)");
      prep.setInt(2, space.getMaxCapacity());
      prep.setString(3, space.getLocation());
      prep.setBoolean(4, space.isAvailable());
      prep.setString(5, space.getEventId());
      do {
        UUID id = UUID.randomUUID();
        space.setId(id.toString());
        prep.setString(1, space.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      throw new Exception(e);
    }
    return space;
  }

  @Override
  public TransferObjectList retrieve(TransferObject params) {
    TransferObjectList result = new TransferObjectList();
    String statement = "SELECT * "
                     + "FROM spaces "
                     + "WHERE id LIKE ? AND "
                           + "max_capacity LIKE ? AND "
                           + "location LIKE ? AND "
                           + "available = ?";
    SpaceTO space = (SpaceTO) params;
    if (!space.getEventId().equals(GenericDAO.ANY_PATTERN)) {
      statement += " AND event_id LIKE ?";
    }
    PreparedStatement prep;
    try {
      prep = conn.prepareStatement(statement);
      prep.setString(1, space.getId());
      if (space.getMaxCapacity() <= 0) {
        prep.setString(2, GenericDAO.ANY_PATTERN);
      }
      else {
        prep.setInt(2, space.getMaxCapacity());
      }
      prep.setString(3, space.getLocation());
      prep.setBoolean(4, space.isAvailable());
      if (!space.getEventId().equals(GenericDAO.ANY_PATTERN)) {
        prep.setString(5, space.getEventId());
      }

      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        SpaceTO row = new SpaceTO();
        row.setId(resultSet.getString("id"));
        row.setMaxCapacity(resultSet.getInt("max_capacity"));
        row.setLocation(resultSet.getString("location"));
        row.setAvailable(resultSet.getBoolean("available"));
        row.setEventId(resultSet.getString("event_id"));
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
    SpaceTO space = null;
    int response = 0;
    try {
      space = (SpaceTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE spaces SET max_capacity = ?, "
              + "location = ?, available = ?, event_id = ? WHERE id = ?");
      prep.setInt(1, space.getMaxCapacity());
      prep.setString(2, space.getLocation());
      prep.setBoolean(3, space.isAvailable());
      prep.setString(4, space.getEventId());
      prep.setString(5,  space.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    SpaceTO space = null;
    int response = 0;
    try {
      space = (SpaceTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM spaces WHERE id = ?");
      prep.setString(1, space.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
