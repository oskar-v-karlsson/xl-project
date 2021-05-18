package model;

import expr.Environment;
import expr.ErrorResult;
import expr.ExprResult;
import util.XLBufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class XLModel implements Environment {
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
    map.put(address.toString(), CellCreator.createContentType(text));

    for(int i=0; i < list.size(); i++){
      list.get(i).modelChange(address.toString(), map.get(address.toString()).getContent(this));
    }

    updateAll();
  }

  private void updateAll(){
    for (Map.Entry<String, CellContent> e: map.entrySet()
         ) {
      for(int i=0; i < list.size(); i++){
        list.get(i).modelChange(e.getKey(), map.get(e.getKey()).getContent(this));
      }
    }
  }

  public void addListener(ModelObserver mo){
    list.add(mo);
  }

  public String getCell(CellAddress address) {
    if(map.get(address.toString()) != null){
      return map.get(address.toString()).getEditorText();
    } else{
      return null;
    }
  }

  public void clearCell(CellAddress address) {
    for(int i=0; i < list.size(); i++){
      list.get(i).modelChange(address.toString(), "");
    }
    map.remove(address);
  }

  public void clearAll() {
    for (Map.Entry<String, CellContent> e: map.entrySet()
         ) {
      for(int i=0; i < list.size(); i++){
        list.get(i).modelChange(e.getKey(), "");
      }
    }
    map.clear();
  }

  public void loadFile(File file) throws FileNotFoundException {
    clearAll();
    XLBufferedReader reader = new XLBufferedReader(file);

    try{
      reader.load(map);
    } catch (IOException e){
      e.printStackTrace();
    }

    updateAll();
  }

  public void saveFile(File file) throws FileNotFoundException {
    XLPrintStream printStream = new XLPrintStream(file.getName());
    printStream.save(map.entrySet());
  }

  @Override
  public ExprResult value(String name) {
    if(map.get(name) != null){
      return map.get(name).value(this);
    } else{
      return new ErrorResult("Referencing empty cell");
    }
  }
}
