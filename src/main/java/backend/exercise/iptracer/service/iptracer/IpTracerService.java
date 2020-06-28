package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.country.CountryInformationResponse;
import backend.exercise.iptracer.service.country.CountryInformationService;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationResponse;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationService;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpTracerService {
    @Autowired
    private IpGeolocalizationService geolocalizationService;

    @Autowired
    private CountryInformationService countryInformationService;

    public IpTracerResponse trace(String ip) {
        IpGeolocalizationResponse geolocalizationResponse = geolocalizationService.getIpGeolocalization(ip);
        CountryInformationResponse countryInformationResponse = countryInformationService.getCountryInformation(
                geolocalizationResponse.getCountryCode());

        IpTracerResponse response = new IpTracerResponse();
        response.setIp(ip);
        response.setIsoCode(geolocalizationResponse.getCountryCode());
        response.setDatetime(LocalDateTime.now());
        response.setCountry(geolocalizationResponse.getCountryCode());
        response.setLanguages(countryInformationResponse.getLanguages());
        response.setCurrencies(countryInformationResponse.getCurrencies());

        return response;
    }
}
