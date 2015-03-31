/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.assistants;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.support.base.TransferObject;
import co.edu.eafit.conferre.support.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.AssistantDAO;
import co.edu.eafit.conferre.support.to.AssistantTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class LoginAssistantUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    AssistantTO assistant = (AssistantTO) params;
    assistant = validateRenterData(assistant);
    AssistantDAO assistantDAO = DAOFactory.createAssistantDAO();
    AssistantTO result;
    try {
      TransferObjectList resultList = assistantDAO.retrieve(assistant);
      if (resultList.getList().isEmpty()) result = null;
      else {
        result = (AssistantTO) resultList.get(0);
      }
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }
  
  private AssistantTO validateRenterData(AssistantTO assistant) throws ValidationException {
    if (assistant.getEmail() == null || assistant.getEmail().equals("")) {
      throw new ValidationException("Email can't be blank");
    }
    if (assistant.getPassword()== null || assistant.getPassword().equals("")) {
      throw new ValidationException("Password can't be blank");
    }
    assistant.setId(GenericDAO.ANY_PATTERN);
    assistant.setName(GenericDAO.ANY_PATTERN);
    assistant.setIdentification(GenericDAO.ANY_PATTERN);
    assistant.setPhoneNumber(GenericDAO.ANY_PATTERN);
    //renter.setMale(true);
    return assistant;
  }
}
