import java.util.Date;

import co.edu.eafit.conferre.conferences.business.RestConferenceFacade;
import co.edu.eafit.conferre.data.to.ConferenceTO;



public class Main {

  public static void main(String [] args) {
    ConferenceTO conference = new ConferenceTO();
    conference.setName("Google recruitment");
    conference.setLecturerName("Santiago Vanegas");
    conference.setType("Ciencias de la computación");
    conference.setDate(new Date());
    conference.setAvailableSeats(80);
    conference.setRenterId("3b105dc6-46f9-441a-8995-18c26db9046b");
    
    RestConferenceFacade restConf = new RestConferenceFacade();
    ConferenceTO result = restConf.createConference(conference);
    System.out.println("Created, id: " + result.getId());
  }
}
