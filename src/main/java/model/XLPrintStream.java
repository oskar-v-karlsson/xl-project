package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class XLPrintStream extends PrintStream {
  public XLPrintStream(String fileName) throws FileNotFoundException {
    super(fileName);
  }

  // TODO Change Object to something appropriate
  public void save(Set<Entry<String, CellContent>> set) {
    for (Map.Entry<String, CellContent> entry : set) {
      if(!(entry.getValue().toString().equals("")) && entry.getValue()!=null)
      print(entry.getKey());
      print('=');
      println(entry.getValue().getEditorText());
    }
    flush();
    close();
  }
}
