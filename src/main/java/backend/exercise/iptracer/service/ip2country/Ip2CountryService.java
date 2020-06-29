package backend.exercise.iptracer.service.ip2country;

import backend.exercise.iptracer.clients.Ip2CountryClient;
import backend.exercise.iptracer.common.CustomJsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class Ip2CountryService {

    private final Ip2CountryClient ip2CountryClient;
    private final CustomJsonMapper customJsonMapper;

    public Ip2CountryService(Ip2CountryClient ip2CountryClient, CustomJsonMapper customJsonMapper) {
        this.ip2CountryClient = ip2CountryClient;
        this.customJsonMapper = customJsonMapper;
    }

    public Ip2CountryResponse getCountry(String ip) {
        String response = ip2CountryClient.getCountry(ip);

        return customJsonMapper.fromJson(response, new TypeReference<Ip2CountryResponse>() {});
    }

}
