package co.edu.eafit.conferre.business.assistants;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.AssistantDAO;
import co.edu.eafit.conferre.data.to.AssistantTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateAssistantUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    AssistantTO assistant = (AssistantTO) params;
    validateAssistantData(assistant);
    AssistantDAO assistantDAO = DAOFactory.createAssistantDAO();
    AssistantTO result;
    try {
      result = (AssistantTO) assistantDAO.create(assistant);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }

  private void validateAssistantData(AssistantTO assistant) throws ValidationException {
    if (assistant.getEmail() == null || assistant.getEmail().equals("")) {
      throw new ValidationException("Assistant email can't be blank");
    }
    if (assistant.getName() == null || assistant.getName().equals("")) {
      throw new ValidationException("Assistant name can't be blank");
    }
  }
}
