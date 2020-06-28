package backend.exercise.iptracer.service.country;

import backend.exercise.iptracer.common.CustomJsonMapper;
import backend.exercise.iptracer.common.HttpConnector;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class CountryInformationService {
    private String url = "https://restcountries.eu/rest/v2/alpha/";
    private final HttpConnector httpConnector;
    private final CustomJsonMapper customJsonMapper;

    public CountryInformationService(HttpConnector httpConnector, CustomJsonMapper customJsonMapper) {
        this.httpConnector = httpConnector;
        this.customJsonMapper = customJsonMapper;
    }

    public CountryInformationResponse getCountryInformation(String countryCode) {
        String response = httpConnector.get(url, countryCode);

        return customJsonMapper.fromJson(response, new TypeReference<CountryInformationResponse>() {});
    }

}
