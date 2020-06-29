package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ip2CountryClient extends HttpClient {
    @Value("${ip2country.baseUrl}")
    private String baseUrl;
    @Value("${ip2country.path}")
    private String path;

    public String get(String ip) {
        String url = baseUrl + path + "?" + ip;
        return super.get(url);
    }
}
