package co.edu.eafit.conferre.data.base;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.edu.eafit.conferre.data.dao.AssistantDAO;
import co.edu.eafit.conferre.data.dao.ConferenceDAO;
import co.edu.eafit.conferre.data.dao.EventDAO;
import co.edu.eafit.conferre.data.dao.RenterDAO;
import co.edu.eafit.conferre.data.dao.SeatDAO;
import co.edu.eafit.conferre.data.dao.SpaceDAO;
import co.edu.eafit.conferre.data.dao.SpaceDBDAO;
import co.edu.eafit.conferre.data.dao.SpaceTXTDAO;
import co.edu.eafit.conferre.data.dao.WaitingListDAO;

public class DAOFactory {
  
  private static Connection conn = null;
  private static File file = null;
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost/conferre";
  
  private static final String USER = "root";
  private static final String PASS = "";
  
  public static final int SOURCE_TEXT = 1;
  public static final int SOURCE_DB = 2;
  
  private static void createConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  public static Connection getConnection() {
    if (conn == null) createConnection();
    return conn;
  }
  
  private static File openFile() {
    if (file == null) file = new File("spaces.txt");
    return file;
  }
  
  public static AssistantDAO createAssistantDAO() {
    return new AssistantDAO(getConnection());
  }
  
  public static ConferenceDAO createConferenceDAO() {
    return new ConferenceDAO(getConnection());
  }
  
  public static EventDAO createEventDAO() {
    return new EventDAO(getConnection());
  }
  
  public static RenterDAO createRenterDAO() {
    return new RenterDAO(getConnection());
  }
  
  public static SeatDAO createSeatDAO() {
    return new SeatDAO(getConnection());
  }
  
  /**
   * @param sourceType 1 for text plain, 2 for database
   * @return SpaceDAO which extends from GenericDAO
   */
  public static SpaceDAO createSpaceDAO(int sourceType) {
    SpaceDAO ret = null;
    switch (sourceType) {
      case SOURCE_TEXT:
        ret = new SpaceTXTDAO(openFile());
        break;
      case SOURCE_DB:
        ret = new SpaceDBDAO(getConnection());
        break;
    }
    return ret;
  }
  
  public static WaitingListDAO createWaitingListDAO() {
    return new WaitingListDAO(getConnection());
  }
}
