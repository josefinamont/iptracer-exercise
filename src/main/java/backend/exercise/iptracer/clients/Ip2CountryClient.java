package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import org.springframework.stereotype.Component;

@Component
public class Ip2CountryClient extends HttpClient {

    private String url = "https://api.ip2country.info/ip?";

    public String get(String ip) {
        return super.get(url + ip);
    }
}
