import java.util.Date;

import co.edu.eafit.conferre.business.assistants.RestAssistantFacade;
import co.edu.eafit.conferre.business.conferences.RestConferenceFacade;
import co.edu.eafit.conferre.business.events.RestEventFacade;
import co.edu.eafit.conferre.business.renters.RestRenterFacade;
import co.edu.eafit.conferre.business.seats.RestSeatFacade;
import co.edu.eafit.conferre.business.spaces.RestSpaceFacade;
import co.edu.eafit.conferre.business.waitinglist.RestWaitingListFacade;
import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.data.to.EventTO;
import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.data.to.SeatTO;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.data.to.WaitingListTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;



public class Main {

  public static EventTO createEvent(ConferenceTO conference) {
    EventTO event = new EventTO();
    event.setName("Sesión 1 - El deporte");
    event.setType("Exposición");
    event.setDescription("Importancia de la salud cardiovascular");
    event.setDate(new Date());
    event.setAvailableSeats(60);
    event.setConferenceId(conference == null ? null : conference.getId());
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
    conference.setName("Agujeros Negros");
    conference.setLecturerName("Hernique Martínez");
    conference.setType("Física");
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
    SpaceTO space = new SpaceTO();
    space.setMaxCapacity(120);
    space.setLocation("Universidad EAFIT, Bloque 19-501");
    space.setAvailable(true);
    space.setEventId(event == null ? null : event.getId());
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
  
  public static SeatTO createSeat(SpaceTO space, AssistantTO assistant) {
    SeatTO seat = new SeatTO();
    seat.setNumber(1);
    seat.setType("VIP");
    seat.setAvailable(true);
    seat.setSpaceId(space == null ? null : space.getId());
    seat.setAssistantId(assistant == null ? null : assistant.getId());
    RestSeatFacade restSeat = new RestSeatFacade();
    SeatTO result;
    try {
      result = restSeat.createSeat(seat);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  
  public static WaitingListTO createWaitingList(ConferenceTO conference, AssistantTO assistant) {
    if (conference == null || assistant == null) return null;
    WaitingListTO waitingList = new WaitingListTO();
    waitingList.setConferenceId(conference.getId());
    waitingList.setAssistantId(assistant.getId());
    RestWaitingListFacade restWaitingList = new RestWaitingListFacade();
    WaitingListTO result;
    try {
      result = restWaitingList.createWaitingList(waitingList);
    }
    catch (UnitOfWorkException e) {
      System.err.println(e.getMessage());
      return null;
    }
    System.out.println("Created, id: " + result.getId());
    return result;
  }
  public static void main(String [] args) {
    //Main.createSpace(null);
    //Main.createSeat(null, null);
    //Main.createConference(null);
    //Main.createSpace(Main.createEvent(Main.createConference(Main.createRenter())));
    ConferenceTO conf = new ConferenceTO();
    conf.setId("c3f7e913-9a30-4072-895c-f9d1dc111f4f");
    AssistantTO assis = new AssistantTO();
    assis.setId("e66bd19d-f5c2-4d4a-9855-a2dd1867520b");
    Main.createWaitingList(conf, assis);
  }
}
