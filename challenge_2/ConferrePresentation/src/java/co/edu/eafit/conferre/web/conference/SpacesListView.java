/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eafit.conferre.web.conference;
import co.edu.eafit.conferre.business.conferences.RestConferenceFacade;
import co.edu.eafit.conferre.data.to.SpaceTO;
import co.edu.eafit.conferre.web.model.Space;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author svanegas
 */
@ManagedBean
@SessionScoped
public class SpacesListView {
  private List<Space> spaces = new ArrayList<Space>();
  private Space space;
  private RestConferenceFacade restConferenceFacade;
  
  public SpacesListView() {
    
  }
  
  /*@PostConstruct
  public void init(){
      for(int i = 1 ; i <= 10 ; i++){
          Space sp = new Space();
          sp.setId(String.valueOf(i));
          sp.setLocation("Location#"+i);
          this.spaces.add(sp);
      }
  }*/
  @PostConstruct
  public void init() {
    List<SpaceTO> spacesTransfer;
    try {
      restConferenceFacade = new RestConferenceFacade();
      spacesTransfer = restConferenceFacade.findEmptySpaces();
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      return;
    }
    for (SpaceTO spaceItem : spacesTransfer) {
      Space newSpace = new Space();
      newSpace.setId(spaceItem.getId());
      newSpace.setLocation(spaceItem.getLocation());
      newSpace.setAvailable(spaceItem.isAvailable());
      newSpace.setMaxCapacity(spaceItem.getMaxCapacity());
      newSpace.setEventId(spaceItem.getEventId());
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
