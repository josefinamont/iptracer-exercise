package backend.exercise.iptracer.service.iptracer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IpTracerResponse {
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("date")
    private String datetime;
    @JsonProperty("country")
    private String country;
    @JsonProperty("iso_code")
    private String isoCode;
    @JsonProperty("languages")
    private List<String> languages;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("times")
    private List<String> times;
    @JsonProperty("estimated_distance")
    private String estimatedDistance;

    public IpTracerResponse(String ip, String datetime, String country, String isoCode, List<String> languages,
                            String currency, List<String> times, String estimatedDistance) {
        this.ip = ip;
        this.datetime = datetime;
        this.country = country;
        this.isoCode = isoCode;
        this.languages = languages;
        this.currency = currency;
        this.times = times;
        this.estimatedDistance = estimatedDistance;
    }
}
