package backend.exercise.iptracer.service.fixer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FixerResponse {
    @JsonProperty("rates")
    private Map<String, Double> rates;

    public FixerResponse() {
        super();
    }

    public FixerResponse(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
