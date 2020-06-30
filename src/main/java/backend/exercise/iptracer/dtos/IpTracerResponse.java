package backend.exercise.iptracer.dtos;

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

    public String getIp() {
        return ip;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getCountry() {
        return country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getTimes() {
        return times;
    }

    public String getEstimatedDistance() {
        return estimatedDistance;
    }
}
