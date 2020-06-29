package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.RestCountriesClient;
import backend.exercise.iptracer.common.CustomJsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class RestCountriesService {
    private final RestCountriesClient restCountriesClient;
    private final CustomJsonMapper customJsonMapper;

    public RestCountriesService(RestCountriesClient restCountriesClient, CustomJsonMapper customJsonMapper) {
        this.restCountriesClient = restCountriesClient;
        this.customJsonMapper = customJsonMapper;
    }

    public RestCountriesResponse getRestCountries(String countryCode) {
        String response = restCountriesClient.get(countryCode);

        return customJsonMapper.fromJson(response, new TypeReference<RestCountriesResponse>() {});
    }

}
