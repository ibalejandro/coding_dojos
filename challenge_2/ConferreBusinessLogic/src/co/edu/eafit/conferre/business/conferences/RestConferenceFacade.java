package co.edu.eafit.conferre.business.conferences;

import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

//@Path("/conferences")
public class RestConferenceFacade implements ConferenceFacade {

  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public ConferenceTO createConference(ConferenceTO conference) throws UnitOfWorkException {
    CreateConferenceUseCase useCase = new CreateConferenceUseCase();
    ConferenceTO conferenceResult = null;
    try {
      conferenceResult = (ConferenceTO) useCase.execute(conference); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return conferenceResult;
  }
}
