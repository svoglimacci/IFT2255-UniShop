package utilities;

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

    public <T> T readJsonFromFile(String filepath, Class<T> valueType) throws IOException {
        File file = new File(filepath);

        if (!file.exists() || file.length() == 0) {
            T defaultValue = mapper.readValue("{}", valueType);
            writeJsonToFile(defaultValue, filepath);
            return defaultValue;
        } else {
            return mapper.readValue(file, valueType);
        }
    }

    public void writeJsonToFile(Object data, String filepath) throws IOException {
        Files.write(Paths.get(filepath), mapper.writeValueAsBytes(data));
    }
}