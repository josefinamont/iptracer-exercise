package backend.exercise.iptracer.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.Reader;

@Component
public class CustomJsonMapper {
    private ObjectMapper mapper;

    public CustomJsonMapper() {
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    public String toJson(Object object) {
        try {
            String jsonString = this.mapper.writeValueAsString(object);
            return jsonString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error creating a json", e);
        }
    }

    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading a json", e);
        }
    }

    public <T> T fromJson(Reader jsonReader, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(jsonReader, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading a json", e);
        }
    }
}
