package org.udem.unishop.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A utility class for handling, reading and writing JSON data.
 */
public class JSONHandler {

  private final ObjectMapper mapper;

  /**
   * Constructs a new JSONHandler with an initialized ObjectMapper.
   */
  public JSONHandler() {
    mapper = new ObjectMapper();
  }

  /**
   * Reads JSON data from a file and converts it to an object of specified type.
   *
   * @param filepath The path to the JSON file.
   * @param valueType The class representing the type of the object to be deserialized.
   * @return The deserialized object.
   * @param <T> The type of the object to be deserialized.
   */
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

  /**
   * Writes an object to a JSON file.
   *
   * @param data The object to be serialized to JSON.
   * @param filepath The path to the JSON file.
   */
  public void writeJsonToFile(Object data, String filepath) {
    try {
      Files.write(Paths.get(filepath), mapper.writeValueAsBytes(data));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}