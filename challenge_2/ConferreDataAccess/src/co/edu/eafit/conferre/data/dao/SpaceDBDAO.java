package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.SpaceTO;

public class SpaceDBDAO implements SpaceDAO {
  
  private Connection conn;
  
  public SpaceDBDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    SpaceTO space = null;
    try {
      space = (SpaceTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO spaces VALUES(?, ?, ?)");
      prep.setInt(1, space.getMaxCapacity());
      prep.setString(2, space.getLocation());
      prep.setBoolean(3, space.isAvailable());
      prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
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
                          + "AND location = ? AND available = ?");
      prep.setInt(1, space.getMaxCapacity());
      prep.setString(2, space.getLocation());
      prep.setBoolean(3, space.isAvailable());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        SpaceTO row = new SpaceTO();
        row.setMaxCapacity(resultSet.getInt("max_capacity"));
        row.setLocation(resultSet.getString("location"));
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
    SpaceTO space = null;
    int response = 0;
    try {
      space = (SpaceTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE spaces SET max_capacity = ?, "
              + "location = ?, available = ? WHERE id = ?");
      prep.setInt(1, space.getMaxCapacity());
      prep.setString(2, space.getLocation());
      prep.setBoolean(3, space.isAvailable());
      prep.setInt(4,  space.getId());
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
      prep.setInt(1, space.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
