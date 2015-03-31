/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.controllers;
import co.edu.eafit.conferre.business.spaces.RestSpaceFacade;
import co.edu.eafit.conferre.support.to.SpaceTO;
import co.edu.eafit.conferre.web.model.Space;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SpacesController {
  private List<Space> spaces;
  private Space space;
  private RestSpaceFacade restConferenceFacade;
  
  @PostConstruct
  public void init() {
    spaces = new ArrayList();
    space = new Space();
    List<SpaceTO> spacesTransfer;
    try {
      restConferenceFacade = new RestSpaceFacade();
      spacesTransfer = restConferenceFacade.findEmptySpaces();
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      return;
    }
    for (SpaceTO spaceItem : spacesTransfer) {
      Space newSpace = new Space();
      newSpace.update(spaceItem);
      spaces.add(newSpace);
    }
  }
  public List<Space> getSpaces() {
    return spaces;
  }
  public void setSpaces(List<Space> spaces) {
    this.spaces = spaces;
  }
  public Space getSpace() {
    return space;
  }
  public void setSpace(Space space) {
    this.space = space;
  }
}
