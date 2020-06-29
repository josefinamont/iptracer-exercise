package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.RestCountriesClient;
import org.springframework.stereotype.Service;

@Service
public class RestCountriesService {
    private final RestCountriesClient restCountriesClient;

    public RestCountriesService(RestCountriesClient restCountriesClient) {
        this.restCountriesClient = restCountriesClient;
    }

    public RestCountriesResponse getRestCountries(String countryCode) {
        return restCountriesClient.getResponse(countryCode);
    }

}
