package backend.exercise.iptracer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpDataDto {
    @JsonProperty("ip")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
