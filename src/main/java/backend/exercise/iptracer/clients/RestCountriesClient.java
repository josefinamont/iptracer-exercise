package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import backend.exercise.iptracer.dtos.RestCountriesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestCountriesClient extends HttpClient {
    @Value("${restcountries.baseUrl}")
    private String baseUrl;
    @Value("${restcountries.path}")
    private String path;

    public RestCountriesResponse getResponse(String countryCode) {
        String url = baseUrl + path + countryCode;
        String response = super.get(url);

        return customMapper.map(response, new TypeReference<RestCountriesResponse>() {});
    }
}
