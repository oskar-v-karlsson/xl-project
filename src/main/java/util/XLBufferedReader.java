package util;

import model.CellAddress;
import model.CellContent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import model.DisplayedContent;

public class XLBufferedReader extends BufferedReader {
  public XLBufferedReader(File file) throws FileNotFoundException {
    super(new FileReader(file));
  }

  // TODO Change Object to something appropriate
  public void load(Map<String, CellContent> map) throws IOException {
    try {
      while (ready()) {
        String string = readLine();
        int i = string.indexOf('=');
        // TODO
        while (string != null){
          String address = string.substring(0, i);
          String value = string.substring(i+1, string.length());
          map.put(address, new DisplayedContent(value));
        }
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
