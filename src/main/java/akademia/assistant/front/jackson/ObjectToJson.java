package akademia.assistant.front.jackson;

import akademia.assistant.front.model.User;
import akademia.assistant.front.repository.Repository;
import akademia.assistant.front.repository.RepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ObjectToJson {
    private ObjectMapper objectMapper = new ObjectMapper();
    public void convertObjectToJsonString(Object object) {
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            System.out.println("JSON Processing Exception.");
        }
    }

    public void convertObjectToJson(Object object, File file) {
        try {
            objectMapper.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
