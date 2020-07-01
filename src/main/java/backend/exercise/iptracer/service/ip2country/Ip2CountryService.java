package backend.exercise.iptracer.service.ip2country;

import backend.exercise.iptracer.clients.Ip2CountryClient;
import backend.exercise.iptracer.dtos.Ip2CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ip2CountryService {
    @Autowired
    private Ip2CountryClient ip2CountryClient;

    public Ip2CountryResponse getCountry(String ip) {
        return ip2CountryClient.getResponse(ip);
    }

}
