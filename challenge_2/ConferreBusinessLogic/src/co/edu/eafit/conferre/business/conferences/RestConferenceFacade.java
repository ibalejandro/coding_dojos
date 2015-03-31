package co.edu.eafit.conferre.business.conferences;

import java.util.List;

import co.edu.eafit.conferre.business.spaces.FindSpacesUseCase;
import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.support.to.ConferenceTO;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

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
  
  @Override
  //@Path("/find")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public List<ConferenceTO> findConferences(ConferenceTO params) 
      throws UnitOfWorkException {
    if (params == null) params = new ConferenceTO();
    FindConferenceUseCase useCase = new FindConferenceUseCase();
    List<ConferenceTO> result;
    try {
      TransferObjectList conferencesResult = 
          (TransferObjectList) useCase.execute(params); 
      result = (List<ConferenceTO>)(List<?>) conferencesResult.getList();
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return result;
  }
  
  
}
