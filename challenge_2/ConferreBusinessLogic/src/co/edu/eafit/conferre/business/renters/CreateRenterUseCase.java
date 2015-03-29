package co.edu.eafit.conferre.business.renters;

import co.edu.eafit.conferre.business.base.UnitOfWork;
import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.dao.RenterDAO;
import co.edu.eafit.conferre.data.to.RenterTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import co.edu.eafit.conferre.support.exceptions.ValidationException;

public class CreateRenterUseCase implements UnitOfWork {
  
  @Override
  public TransferObject execute(TransferObject params) throws UnitOfWorkException {
    RenterTO renter = (RenterTO) params;
    validateRenterData(renter);
    RenterDAO renterDAO = FactoryDAO.createRenterDAO();
    RenterTO result;
    try {
      result = (RenterTO) renterDAO.create(renter);
    }
    catch (Exception e) {
      throw new UnitOfWorkException(e);
    }
    return result;
  }
  
  private void validateRenterData(RenterTO renter) throws ValidationException {
    if (renter.getEmail() == null || renter.getEmail().equals("")) {
      throw new ValidationException("Renter email can't be blank");
    }
    if (renter.getName() == null || renter.getName().equals("")) {
      throw new ValidationException("Renter name can't be blank");
    }
  }
}
