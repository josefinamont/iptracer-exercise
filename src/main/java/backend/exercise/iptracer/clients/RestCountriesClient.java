package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestCountriesClient extends HttpClient {
    @Value("${restcountries.baseUrl}")
    private String baseUrl;
    @Value("${restcountries.path}")
    private String path;

    public String get(String countryCode) {
        String url = baseUrl + path + countryCode;
        return super.get(url);
    }
}
