package backend.exercise.iptracer.repository;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FixerRepository {
    private Map<String, Double> rates = new ConcurrentHashMap<>();

    public Optional<Double> getRateForCurrency(String currencyCode) {
        return Optional.ofNullable(rates.get(currencyCode));
    }

    public void withRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public boolean isEmpty() {
        return rates.isEmpty();
    }
}
