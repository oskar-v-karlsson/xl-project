package model;

import util.XLBufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class XLModel {
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
}
