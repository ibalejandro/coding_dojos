package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;

public interface SpaceFacade {
  public SpaceTO createSpace(SpaceTO space) throws UnitOfWorkException;
}
