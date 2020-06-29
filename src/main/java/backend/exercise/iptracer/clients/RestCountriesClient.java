package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import org.springframework.stereotype.Component;

@Component
public class RestCountriesClient extends HttpClient {
    private String url = "https://restcountries.eu/rest/v2/alpha/";

    public String get(String ip) {
        return super.get(url + ip);
    }
}
