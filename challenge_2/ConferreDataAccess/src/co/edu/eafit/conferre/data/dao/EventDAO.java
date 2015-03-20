package co.edu.eafit.conferre.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.data.to.EventTO;

public class EventDAO implements GenericDAO {

  Connection conn;
  
  public EventDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    EventTO event = null;
    try {
      event = (EventTO) newObject;
      PreparedStatement prep = conn
          .prepareStatement("INSERT INTO events VALUES(?, ?, ?, ?, ?)");
      prep.setString(1, event.getName());
      prep.setString(2, event.getType());
      prep.setString(3, event.getDescription());
      
      Date date = new Date(event.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  event.getAvailableSeats());
      prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return event;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    EventTO event = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      event = (EventTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM events WHERE name = ?"
                          + "OR type = ? OR description = ? OR date = ? "
                          + "OR available_seats = ?");
      prep.setString(1, event.getName());
      prep.setString(2, event.getType());
      prep.setString(3, event.getDescription());
      Date date = new Date(event.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  event.getAvailableSeats());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        ConferenceTO row = new ConferenceTO();
        row.setName(resultSet.getString("name"));
        row.setLecturerName(resultSet.getString("type"));
        row.setType(resultSet.getString("description"));
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
    EventTO event = null;
    int response = 0;
    try {
      event = (EventTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE events SET name = ?, "
              + "type = ?, description = ?, date = ?, available_seats = ? "
              + "WHERE id = ?");
      prep.setString(1, event.getName());
      prep.setString(2, event.getType());
      prep.setString(3, event.getDescription());
      
      Date date = new Date(event.getDate().getTime());
      prep.setDate(4, date);
      prep.setInt(5,  event.getAvailableSeats());
      prep.setInt(6, event.getId());
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
      prep.setInt(1, event.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
