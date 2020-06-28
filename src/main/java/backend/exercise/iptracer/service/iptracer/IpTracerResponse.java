package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.country.Currency;
import backend.exercise.iptracer.service.country.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class IpTracerResponse {
    private String ip;
    private String datetime;
    private String country;
    private String isoCode;
    private List<Language> languages;
    private List<Currency> currencies;
    private List<String> timezones;
    @JsonProperty("estimated_distance")
    private String estimatedDistance;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.datetime = datetime.toString(fmt);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public List<String> getLanguages() {
        return languages.stream().map(Language::buildLanguage).collect(Collectors.toList());
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getCurrency() {
        return this.currencies.stream().findFirst().get().getCode();
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public String getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(String estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }
}
