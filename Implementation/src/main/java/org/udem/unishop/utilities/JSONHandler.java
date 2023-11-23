package org.udem.unishop.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {

  private final ObjectMapper mapper;

  public JSONHandler() {
    mapper = new ObjectMapper();
  }

  public <T> T readJsonFromFile(String filepath, Class<T> valueType) {
    try {
      File file = new File(filepath);

      if (!file.exists() || file.length() == 0) {
        return null;
      } else {
        return mapper.readValue(file, valueType);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void writeJsonToFile(Object data, String filepath) {
    try {
      Files.write(Paths.get(filepath), mapper.writeValueAsBytes(data));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}