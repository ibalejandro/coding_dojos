package co.edu.eafit.conferre.support.base;

import java.util.ArrayList;
import java.util.List;

public class TransferObjectList implements TransferObject {
  
  private List<TransferObject> list;
  
  public TransferObjectList() {
    list = new ArrayList<TransferObject>();
  }
  
  public TransferObjectList(List<TransferObject> list) {
    this.list = list;
  }
  
  public List<TransferObject> getList() {
    return list;
  }
  
  public void setList(List<TransferObject> list) {
    this.list = list;
  }
  
  public void add(TransferObject item) {
    list.add(item);
  }
  
  public TransferObject get(int index) {
    if (index >= list.size()) return null;
    return list.get(index);
  }
}
