package backend.exercise.iptracer.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

@Component
public class CustomMapper {
    private ObjectMapper mapper;

    public CustomMapper() {
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true);
    }

    public <T> T map(String json, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading json", e);
        }
    }

    public <T> T map(Reader json, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading json", e);
        }
    }
}
