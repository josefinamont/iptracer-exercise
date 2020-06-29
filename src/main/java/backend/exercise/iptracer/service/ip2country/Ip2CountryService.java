package backend.exercise.iptracer.service.ip2country;

import backend.exercise.iptracer.clients.Ip2CountryClient;
import backend.exercise.iptracer.common.CustomMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class Ip2CountryService {
    private final Ip2CountryClient ip2CountryClient;
    private final CustomMapper customMapper;

    public Ip2CountryService(Ip2CountryClient ip2CountryClient, CustomMapper customMapper) {
        this.ip2CountryClient = ip2CountryClient;
        this.customMapper = customMapper;
    }

    public Ip2CountryResponse getCountry(String ip) {
        String response = ip2CountryClient.get(ip);

        return customMapper.parse(response, new TypeReference<Ip2CountryResponse>() {});
    }

}
