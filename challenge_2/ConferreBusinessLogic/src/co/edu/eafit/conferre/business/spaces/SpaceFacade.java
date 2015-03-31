package co.edu.eafit.conferre.business.spaces;

import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.support.exceptions.UnitOfWorkException;
import java.util.List;

public interface SpaceFacade {
  public SpaceTO createSpace(SpaceTO space) throws UnitOfWorkException;
  public List<SpaceTO> findEmptySpaces() throws UnitOfWorkException;
}
