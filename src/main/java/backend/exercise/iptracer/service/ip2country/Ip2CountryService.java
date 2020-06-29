package backend.exercise.iptracer.service.ip2country;

import backend.exercise.iptracer.common.HttpConnector;
import backend.exercise.iptracer.common.CustomJsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class Ip2CountryService {
    private String url = "https://api.ip2country.info/ip?";
    private final HttpConnector httpConnector;
    private final CustomJsonMapper customJsonMapper;

    public Ip2CountryService(HttpConnector httpConnector, CustomJsonMapper customJsonMapper) {
        this.httpConnector = httpConnector;
        this.customJsonMapper = customJsonMapper;
    }

    public Ip2CountryResponse getIpGeolocalization(String ip) {
        String response = httpConnector.get(url, ip);

        return customJsonMapper.fromJson(response, new TypeReference<Ip2CountryResponse>() {});
    }

}
