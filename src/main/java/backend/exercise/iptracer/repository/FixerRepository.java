package backend.exercise.iptracer.repository;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class FixerRepository {
    private Map<String, Double> rates = new HashMap<>();

    public Optional<Double> getRateForCurrency(String currencyCode) {
        return Optional.ofNullable(rates.get(currencyCode));
    }

    public void withRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
