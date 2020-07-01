package backend.exercise.iptracer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpDataDto {
    @JsonProperty("ip")
    private String ip;

    public IpDataDto(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
