package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class JSONHandler<T> {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JSONHandler(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public Set<T> read(Class<T> classType) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(Set.class, classType);
            return objectMapper.readValue(new File(this.filePath), type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean write(Set<T> set) {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            fw.write(Objects.requireNonNull(this.objToJson(set)));
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String objToJson(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(obj);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T jsonToObj(String json, Class<T> type) {
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
