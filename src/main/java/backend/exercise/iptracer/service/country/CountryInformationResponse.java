package backend.exercise.iptracer.service.country;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CountryInformationResponse {
    @JsonProperty
    private List<Language> languages;
    @JsonProperty
    private List<Currency> currencies;

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
