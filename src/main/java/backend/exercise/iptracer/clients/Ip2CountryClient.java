package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import backend.exercise.iptracer.service.ip2country.Ip2CountryResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ip2CountryClient extends HttpClient {
    @Value("${ip2country.baseUrl}")
    private String baseUrl;
    @Value("${ip2country.path}")
    private String path;

    public Ip2CountryResponse getResponse(String ip) {
        String url = baseUrl + path + "?" + ip;
        String response = super.get(url);
        
        return customMapper.map(response, new TypeReference<Ip2CountryResponse>() {});
    }
}
