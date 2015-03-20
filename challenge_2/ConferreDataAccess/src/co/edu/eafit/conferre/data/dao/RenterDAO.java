package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.RenterTO;

public class RenterDAO implements GenericDAO {

  private Connection conn;
  
  public RenterDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    RenterTO renter = null;
    try {
      renter = (RenterTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO renters VALUES(?, ?, ?, ?, ?)");
      prep.setString(1, renter.getName());
      prep.setString(2, renter.getIdentification());
      prep.setString(3, renter.getPhoneNumber());
      prep.setString(4, renter.getEmail());
      prep.setBoolean(5, renter.isMale());
      prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return renter;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    RenterTO renter = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      renter = (RenterTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM renters WHERE name = ?"
                          + "AND identification = ? AND phone_number = ? AND "
                          + "email = ? AND gender = ?");
      prep.setString(1, renter.getName());
      prep.setString(2, renter.getIdentification());
      prep.setString(3, renter.getPhoneNumber());
      prep.setString(4, renter.getEmail());
      prep.setBoolean(5, renter.isMale());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        RenterTO row = new RenterTO();
        row.setName(resultSet.getString("name"));
        row.setIdentification(resultSet.getString("identification"));
        row.setPhoneNumber(resultSet.getString("phone_number"));
        row.setEmail(resultSet.getString("email"));
        row.setMale(resultSet.getBoolean("gender"));
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
    RenterTO renter = null;
    int response = 0;
    try {
      renter = (RenterTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE renters SET name = ?, "
              + "identification = ?, phone_number = ?, email = ?, gender = ? "
              + "WHERE id = ?");
      prep.setString(1, renter.getName());
      prep.setString(2, renter.getIdentification());
      prep.setString(3, renter.getPhoneNumber());
      prep.setString(5,  renter.getEmail());
      prep.setBoolean(6, renter.isMale());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    RenterTO renter = null;
    int response = 0;
    try {
      renter = (RenterTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM renters WHERE id = ?");
      prep.setInt(1, renter.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
