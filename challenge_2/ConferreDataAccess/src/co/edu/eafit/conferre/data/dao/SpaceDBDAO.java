package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.eafit.conferre.data.base.TransferObject;
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
  public List<TransferObject> retrieve(TransferObject params) {
    SpaceTO space = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      space = (SpaceTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM spaces WHERE max_capacity = ?"
                          + "AND location = ? AND available = ? AND "
                          + "event_id = ?");
      prep.setInt(1, space.getMaxCapacity());
      prep.setString(2, space.getLocation());
      prep.setBoolean(3, space.isAvailable());
      prep.setString(4, space.getEventId());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        SpaceTO row = new SpaceTO();
        row.setMaxCapacity(resultSet.getInt("max_capacity"));
        row.setLocation(resultSet.getString("location"));
        row.setAvailable(resultSet.getBoolean("available"));
        row.setEventId(resultSet.getString("event_id)"));
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
      prep.setInt(2, space.getMaxCapacity());
      prep.setString(3, space.getLocation());
      prep.setBoolean(4, space.isAvailable());
      prep.setString(5, space.getEventId());
      prep.setString(1,  space.getId());
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
