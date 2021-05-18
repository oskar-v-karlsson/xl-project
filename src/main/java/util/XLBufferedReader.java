package util;

import model.CellContent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import model.CellCreator;

public class XLBufferedReader extends BufferedReader {
  public XLBufferedReader(File file) throws FileNotFoundException {
    super(new FileReader(file));
  }

  // TODO Change Object to something appropriate
  public void load(Map<String, CellContent> map) throws IOException {
    try {
      while (ready()) {
        String string = readLine();
        String[] objects = string.split("=", 2);

        String address = objects[0];
        String value = objects[1];

        map.put(address, CellCreator.createContentType(value));
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
