package backend.exercise.iptracer.service.geolocalization;

import backend.exercise.iptracer.common.HttpConnector;
import backend.exercise.iptracer.common.CustomJsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class IpGeolocalizationService {
    private String url = "https://api.ip2country.info/ip?";
    private final HttpConnector httpConnector;
    private final CustomJsonMapper customJsonMapper;

    public IpGeolocalizationService(HttpConnector httpConnector, CustomJsonMapper customJsonMapper) {
        this.httpConnector = httpConnector;
        this.customJsonMapper = customJsonMapper;
    }

    public IpGeolocalizationResponse getIpGeolocalization(String ip) {
        String response = httpConnector.get(url, ip);

        return customJsonMapper.fromJson(response, new TypeReference<IpGeolocalizationResponse>() {});
    }

}
