package model;

import expr.Environment;
import expr.ExprResult;
import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;
import util.XLBufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class XLModel extends ObservableMap implements Environment {
  public static final int COLUMNS = 10, ROWS = 10;
  private Map<String, CellContent> map;
  private List<ModelObserver> list;

  public XLModel(){

    map = new HashMap<>();
    list = new ArrayList<>();
  }

  /**
   * Called when the code for a cell changes.
   *
   * @param address address of the cell that is being edited
   * @param text    the new code for the cell - can be raw text (starting with #) or an expression
   */
  public void update(CellAddress address, String text) {
    if(map.containsKey(address.toString())){
      map.get(address.toString()).update(text);
    } else{
      if(text.startsWith("#")){
        map.put(address.toString(), new DisplayedContent(text));
      } else{
        map.put(address.toString(), new ExpressionContent(text));
      }
    }

    for(int i=0; i < list.size(); i++){
      list.get(i).modelChange(address, String.valueOf(map.get(address.toString()).value(this).value()));
    }
  }

  @Override
  public void addListener(ModelObserver mo){
    list.add(mo);
  }

  public String getCell(CellAddress address) {
    if(map.get(address) != null){
      return map.get(address.toString()).value(this).toString();
    }
    return null;
  }

  /*public String getCellContent(CellAddress address){
    return getCell(address).getContent();
  }*/



  public void clearCell(CellAddress address) {

    update(address,null);
  }

  public void clearAll() {
    for (String address: map.keySet()) {
      map.put(address, null);
    }
  }

  public void loadFile(File file) throws FileNotFoundException {
    XLBufferedReader reader = new XLBufferedReader(file);

    try{
      reader.load(map);
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public void saveFile(File file) throws FileNotFoundException {
    XLPrintStream printStream = new XLPrintStream(file.getName());
    printStream.save(map.entrySet());
  }

  @Override
  public ExprResult value(String name) {
    return map.get(name).value(this);
  }
}
