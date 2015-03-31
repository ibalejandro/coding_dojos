package co.edu.eafit.conferre.business.conferences;

import java.util.List;

import co.edu.eafit.conferre.support.to.ConferenceTO;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface ConferenceFacade {
  public ConferenceTO createConference(ConferenceTO conference) throws UnitOfWorkException;
  public List<ConferenceTO> findConferences(ConferenceTO conference) throws UnitOfWorkException;
  
}
