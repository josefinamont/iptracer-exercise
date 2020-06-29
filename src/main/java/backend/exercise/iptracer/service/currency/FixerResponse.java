package backend.exercise.iptracer.service.currency;

import java.util.Map;

public class FixerResponse {
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public Double getRate() {
        return rates.get("USD");
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
