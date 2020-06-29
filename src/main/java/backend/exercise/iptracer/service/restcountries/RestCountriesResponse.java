package backend.exercise.iptracer.service.restcountries;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RestCountriesResponse {
    @JsonProperty
    private List<Language> languages;
    @JsonProperty
    private List<Currency> currencies;
    @JsonProperty
    private List<String> timezones;
    @JsonProperty
    private List<Double> latlng;

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getCurrency() {
        return currencies.stream().map(Currency::getCode).findFirst().get();
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

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }
}
