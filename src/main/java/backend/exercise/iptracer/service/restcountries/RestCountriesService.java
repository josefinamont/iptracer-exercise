package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.RestCountriesClient;
import backend.exercise.iptracer.common.CustomMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class RestCountriesService {
    private final RestCountriesClient restCountriesClient;
    private final CustomMapper customMapper;

    public RestCountriesService(RestCountriesClient restCountriesClient, CustomMapper customMapper) {
        this.restCountriesClient = restCountriesClient;
        this.customMapper = customMapper;
    }

    public RestCountriesResponse getRestCountries(String countryCode) {
        String response = restCountriesClient.get(countryCode);

        return customMapper.parse(response, new TypeReference<RestCountriesResponse>() {});
    }

}
