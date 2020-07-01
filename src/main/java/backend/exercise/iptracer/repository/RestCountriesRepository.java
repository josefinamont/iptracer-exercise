package backend.exercise.iptracer.repository;

import backend.exercise.iptracer.dtos.RestCountry;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RestCountriesRepository {
    private Map<String, RestCountry> countryInfos = new ConcurrentHashMap<>();

    public Optional<RestCountry> getCountryInfo(String countryCode) {
        return Optional.ofNullable(countryInfos.get(countryCode));
    }

    public void withCountryInfo(String countryCode, RestCountry countryInfo) {
        this.countryInfos.put(countryCode, countryInfo);
    }

    public boolean isEmpty() {
        return countryInfos.isEmpty();
    }
}
