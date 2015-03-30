/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.DAOFactory;
import co.edu.eafit.conferre.data.base.GenericDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.base.TransferObjectList;
import co.edu.eafit.conferre.data.dao.RenterDAO;
import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class LoginRenterUseCase implements UnitOfWork{
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    RenterTO renter = (RenterTO) params;
    renter = validateRenterData(renter);
    RenterDAO renterDAO = DAOFactory.createRenterDAO();
    RenterTO result;
    try {
      TransferObjectList resultList = renterDAO.retrieve(renter);
      if (resultList.getList().isEmpty()) result = null;
      else {
        result = (RenterTO) resultList.get(0);
      }
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }
  
  private RenterTO validateRenterData(RenterTO renter) throws ValidationException {
    if (renter.getEmail() == null || renter.getEmail().equals("")) {
      throw new ValidationException("Email can't be blank");
    }
    if (renter.getPassword()== null || renter.getPassword().equals("")) {
      throw new ValidationException("Password can't be blank");
    }
    renter.setId(GenericDAO.ANY_PATTERN);
    renter.setName(GenericDAO.ANY_PATTERN);
    renter.setIdentification(GenericDAO.ANY_PATTERN);
    renter.setPhoneNumber(GenericDAO.ANY_PATTERN);
    //renter.setMale(true);
    return renter;
  }
}
