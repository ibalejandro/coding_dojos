package co.edu.eafit.conferre.business.assistants;

import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface AssistantFacade {
  public AssistantTO createAssistant(AssistantTO assistant) throws UnitOfWorkException;
}
