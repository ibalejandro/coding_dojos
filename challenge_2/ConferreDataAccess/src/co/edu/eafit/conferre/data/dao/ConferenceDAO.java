package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.ConferenceTO;

public class ConferenceDAO implements GenericDAO {

  private Connection conn;
  
  public ConferenceDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    // Podemos gener un UUID para guardarlo como id, y si ya existe (muy poco 
    // probable) entonces volvemos a intentar insertarlo.
    ConferenceTO conference = null;
    try {
      conference = (ConferenceTO) newObject;
      PreparedStatement prep = conn
       .prepareStatement("INSERT INTO conferences VALUES(?, ?, ?, ?, ?, ?, ?)");
      prep.setString(2, conference.getName());
      prep.setString(3, conference.getLecturerName());
      prep.setString(4, conference.getType());
      Date date = new Date(conference.getDate().getTime());
      prep.setDate(5, date);
      prep.setInt(6,  conference.getAvailableSeats());
      prep.setInt(7, conference.getRenterId());
      do {
        UUID id = UUID.randomUUID();
        conference.setId(id.toString());
        prep.setString(1, conference.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return conference;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    ConferenceTO conference = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      conference = (ConferenceTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM conferences WHERE name = ?"
                          + "OR lecturer_name = ? OR type = ? OR date = ? "
                          + "OR available_seats = ?");
      prep.setString(1, conference.getName());
      prep.setString(2, conference.getLecturerName());
      prep.setString(3, conference.getType());
      Date date = new Date(conference.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  conference.getAvailableSeats());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        ConferenceTO row = new ConferenceTO();
        row.setName(resultSet.getString("name"));
        row.setLecturerName(resultSet.getString("lecturer_name"));
        row.setType(resultSet.getString("type"));
        row.setDate(resultSet.getTimestamp("date"));
        row.setAvailableSeats(resultSet.getInt("available_seats"));
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
    ConferenceTO conference = null;
    int response = 0;
    try {
      conference = (ConferenceTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE conferences SET name = ?, "
              + "lecturer_name = ?, type = ?, date = ?, available_seats = ? "
              + "WHERE id = ?");
      prep.setString(1, conference.getName());
      prep.setString(2, conference.getLecturerName());
      prep.setString(3, conference.getType());
      
      Date date = new Date(conference.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  conference.getAvailableSeats());
      prep.setString(6, conference.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    ConferenceTO conference = null;
    int response = 0;
    try {
      conference = (ConferenceTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM conferences WHERE id = ?");
      prep.setString(1, conference.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
