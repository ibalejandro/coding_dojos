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
import co.edu.eafit.conferre.data.to.AssistantTO;

public class AssistantDAO implements GenericDAO {

  private Connection conn;
  
  public AssistantDAO(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    AssistantTO assistant = null;
    try {
      assistant = (AssistantTO) newObject;
      PreparedStatement prep = conn
        .prepareStatement("INSERT INTO assistants VALUES(?, ?, ?, ?, ?, ?, ?)");
      prep.setString(2, assistant.getName());
      prep.setString(3, assistant.getIdentification());
      prep.setString(4, assistant.getPhoneNumber());
      prep.setString(5, assistant.getEmail());
      prep.setString(6, assistant.getPassword());
      prep.setBoolean(7, assistant.isMale());
      do {
        UUID id = UUID.randomUUID();
        assistant.setId(id.toString());
        prep.setString(1, assistant.getId());
      } while (prep.executeUpdate() == 0);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return assistant;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    AssistantTO assistant = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    PreparedStatement prep;
    try {
      assistant = (AssistantTO) params;
      prep = conn
          .prepareStatement("SELECT * FROM assistants WHERE name = ?"
                          + "AND identification = ? AND phone_number = ? AND "
                          + "email = ? AND gender = ?");
      prep.setString(1, assistant.getName());
      prep.setString(2, assistant.getIdentification());
      prep.setString(3, assistant.getPhoneNumber());
      prep.setString(4, assistant.getEmail());
      prep.setBoolean(5, assistant.isMale());
      ResultSet resultSet = prep.executeQuery();
      while (resultSet.next()) {
        AssistantTO row = new AssistantTO();
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
    AssistantTO assistant = null;
    int response = 0;
    try {
      assistant = (AssistantTO) object;
      PreparedStatement prep = conn
          .prepareStatement("UPDATE assistants SET name = ?, "
              + "identification = ?, phone_number = ?, email = ?, gender = ? "
              + "WHERE id = ?");
      prep.setString(1, assistant.getName());
      prep.setString(2, assistant.getIdentification());
      prep.setString(3, assistant.getPhoneNumber());
      prep.setString(4,  assistant.getEmail());
      prep.setBoolean(5, assistant.isMale());
      prep.setString(6, assistant.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    AssistantTO assistant = null;
    int response = 0;
    try {
      assistant = (AssistantTO) params;
      PreparedStatement prep = conn
          .prepareStatement("DELETE FROM assistants WHERE id = ?");
      prep.setString(1, assistant.getId());
      response = prep.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return response;
  }
}
