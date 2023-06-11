package akademia.assistant.front.jackson;

import akademia.assistant.front.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
    private ObjectMapper objectMapper = new ObjectMapper();
    public void convertObjectToJson(Object object) {
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            System.out.println("JSON Processing Exception.");
        }
    }
}
