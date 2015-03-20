package co.edu.eafit.conferre.conferences.business;

import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

@Path("/conferences")
public class RestConferenceFacade implements ConferencesFacade {

  @Override
  @Path("/")
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  //Jersey y nosequé json
  public ConferenceTO createConference(ConferenceTO conference) {
    CreateConferenceUseCase useCase = new CreateConferenceUseCase();
    ConferenceTO conferenceResult = null;
    try {
      conferenceResult = (ConferenceTO) useCase.execute(conference); 
    }
    catch (UnitOfWorkException e) {
      
    }
    return conferenceResult;
  }

}
