package backend.exercise.iptracer.service.ip2country;

import backend.exercise.iptracer.clients.Ip2CountryClient;
import org.springframework.stereotype.Service;

@Service
public class Ip2CountryService {
    private final Ip2CountryClient ip2CountryClient;

    public Ip2CountryService(Ip2CountryClient ip2CountryClient) {
        this.ip2CountryClient = ip2CountryClient;
    }

    public Ip2CountryResponse getCountry(String ip) {
        return ip2CountryClient.getResponse(ip);
    }

}
