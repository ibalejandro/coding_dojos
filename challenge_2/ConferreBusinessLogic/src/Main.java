import java.util.Date;

import co.edu.eafit.conferre.business.assistants.RestAssistantFacade;
import co.edu.eafit.conferre.business.conferences.RestConferenceFacade;
import co.edu.eafit.conferre.business.event.RestEventFacade;
import co.edu.eafit.conferre.business.renters.RestRenterFacade;
import co.edu.eafit.conferre.business.spaces.RestSpaceFacade;
import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;



public class Main {

  public static EventTO createEvent(ConferenceTO conference) {
    if (conference == null) return null;
    EventTO event = new EventTO();
    event.setName("Sesión 1 - El deporte");
    event.setType("Exposición");
    event.setDescription("Importancia de la salud cardiovascular");
    event.setDate(new Date());
    event.setAvailableSeats(60);
    event.setConferenceId(conference.getId());
    RestEventFacade restEvent = new RestEventFacade();
    EventTO result;
    try {
      result = restEvent.createEvent(event);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static AssistantTO createAssistant() {
    AssistantTO assistant = new AssistantTO();
    assistant.setName("Federica Zuluaga");
    assistant.setIdentification("1036151251");
    assistant.setPhoneNumber("3151612412");
    assistant.setEmail("fedezulu@gmail.com");
    assistant.setPassword("123");
    assistant.setMale(false);
    RestAssistantFacade restAssistant = new RestAssistantFacade();
    AssistantTO result;
    try {
      result = restAssistant.createAssistant(assistant);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static ConferenceTO createConference(RenterTO renter) {
    if (renter == null) return null;
    ConferenceTO conference = new ConferenceTO();
    conference.setName("Los beneficios de la salud");
    conference.setLecturerName("Uriel Hernandez");
    conference.setType("Salud");
    conference.setDate(new Date());
    conference.setAvailableSeats(60);
    conference.setRenterId(renter.getId());
    
    RestConferenceFacade restConf = new RestConferenceFacade();
    ConferenceTO result;
    try {
      result = restConf.createConference(conference);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static RenterTO createRenter() {
    RenterTO renter = new RenterTO();
    renter.setName("Jesús Estiven Lopera Jaramillo");
    renter.setIdentification("1037124512");
    renter.setPhoneNumber("319516123");
    renter.setEmail("jlopera8@eafit.edu.co");
    renter.setPassword("123");
    renter.setMale(true);
    
    RestRenterFacade restRenter = new RestRenterFacade();
    RenterTO result;
    try {
      result = restRenter.createRenter(renter);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static SpaceTO createSpace(EventTO event) {
    if (event == null) return null;
    SpaceTO space = new SpaceTO();
    space.setMaxCapacity(120);
    space.setLocation("Universidad EAFIT, Bloque 35-108");
    space.setAvailable(true);
    space.setEventId(event.getId());
    RestSpaceFacade restSpace = new RestSpaceFacade();
    SpaceTO result;
    try {
      result = restSpace.createSpace(space);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static void main(String [] args) {
    Main.createSpace(Main.createEvent(Main.createConference(Main.createRenter())));
  }
}
