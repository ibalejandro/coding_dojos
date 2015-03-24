package co.edu.eafit.conferre.business.conferences;

import co.edu.eafit.conferre.data.to.ConferenceTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public interface ConferenceFacade {
  public ConferenceTO createConference(ConferenceTO conference) throws UnitOfWorkException;
}
