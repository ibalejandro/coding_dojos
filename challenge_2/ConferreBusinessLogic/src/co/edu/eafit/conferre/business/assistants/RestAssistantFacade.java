package co.edu.eafit.conferre.business.assistants;

import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.support.UnitOfWorkException;

public class RestAssistantFacade implements AssistantFacade {
  
  @Override
  //@Path("/")
  //@POST
  //@Consumes("application/json")
  //@Produces("application/json")
  //Jersey y nosequé json
  public AssistantTO createAssistant(AssistantTO assistant) throws UnitOfWorkException {
    CreateAssistantUseCase useCase = new CreateAssistantUseCase();
    AssistantTO assistantResult = null;
    try {
      assistantResult = (AssistantTO) useCase.execute(assistant); 
    }
    catch (UnitOfWorkException e) {
      throw e;
    }
    return assistantResult;
  }
}
