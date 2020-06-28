package backend.exercise.iptracer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class IpDataDto {
    //@Id
    @JsonProperty("ip")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
