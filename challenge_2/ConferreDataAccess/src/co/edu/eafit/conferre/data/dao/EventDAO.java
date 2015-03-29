package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.to.EventTO;

public class EventDAO implements GenericDAO {

  Connection conn;
  
  public EventDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) throws Exception {
    EventTO event = null;
    try {
      event = (EventTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO events VALUES(?, ?, ?, ?, ?, ?, ?)");
      prep.setString(2, event.getName());
      prep.setString(3, event.getType());
      prep.setString(4, event.getDescription());
      
      Date date = new Date(event.getDate().getTime());
      prep.setDate(5, date);
      prep.setInt(6,  event.getAvailableSeats());
      prep.setString(7, event.getConferenceId());
      do {
        UUID id = UUID.randomUUID();
        event.setId(id.toString());
        prep.setString(1, event.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      throw new Exception(e);
    }
    return event;
  }

  @Override
  public TransferObjectList retrieve(TransferObject params) {
    EventTO event = null;
    TransferObjectList result = new TransferObjectList();
    PreparedStatement prep;
    try {
      event = (EventTO) params;
      prep = conn
          .prepareStatement("SELECT * "
                          + "FROM events "
                          + "WHERE id LIKE ? AND "
                                + "name LIKE ? AND "
                                + "type LIKE ? AND "
                                + "description LIKE ? AND "
                                + "date LIKE ? AND "
                                + "available_seats LIKE ? AND "
                                + "conference_id LIKE ?");

      prep.setString(1, event.getId());
      prep.setString(2, event.getName());
      prep.setString(3, event.getType());
      prep.setString(4, event.getDescription());
      if (event.getDate() == null) {
        prep.setString(5, GenericDAO.ANY_PATTERN);
      }
      else {
        Date date = new Date(event.getDate().getTime());
        prep.setDate(5, date);
      }
      if (event.getAvailableSeats() < 0) {
        prep.setString(6, GenericDAO.ANY_PATTERN);
      }
      else {
        prep.setInt(6,  event.getAvailableSeats());
      }
      prep.setString(7, event.getConferenceId());

      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        EventTO row = new EventTO();
        row.setId(resultSet.getString("id"));
        row.setName(resultSet.getString("name"));
        row.setType(resultSet.getString("type"));
        row.setDescription(resultSet.getString("description"));
        row.setDate(resultSet.getTimestamp("date"));
        row.setAvailableSeats(resultSet.getInt("available_seats"));
        row.setConferenceId(resultSet.getString("conference_id"));
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
    EventTO event = null;
    int response = 0;
    try {
      event = (EventTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE events SET name = ?, "
              + "type = ?, description = ?, date = ?, available_seats = ?,"
              + "conference_id = ? WHERE id = ?");
      prep.setString(1, event.getName());
      prep.setString(2, event.getType());
      prep.setString(3, event.getDescription());
      
      Date date = new Date(event.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  event.getAvailableSeats());
      prep.setString(6, event.getConferenceId());
      prep.setString(7, event.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    EventTO event = null;
    int response = 0;
    try {
      event = (EventTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM events WHERE id = ?");
      prep.setString(1, event.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
