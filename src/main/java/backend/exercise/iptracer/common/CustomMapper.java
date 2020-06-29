package backend.exercise.iptracer.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomMapper {
    private ObjectMapper mapper;

    public CustomMapper() {
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T map(String json, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading json", e);
        }
    }
}
