package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.model.exceptions.InvalidIpException;
import backend.exercise.iptracer.service.DistanceHelper;
import backend.exercise.iptracer.service.fixer.FixerResponse;
import backend.exercise.iptracer.service.fixer.FixerService;
import backend.exercise.iptracer.service.ip2country.Ip2CountryResponse;
import backend.exercise.iptracer.service.ip2country.Ip2CountryService;
import backend.exercise.iptracer.service.restcountries.RestCountriesService;
import backend.exercise.iptracer.service.restcountries.RestCountry;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpTracerService {
    @Autowired
    private Ip2CountryService ip2CountryService;
    @Autowired
    private RestCountriesService restCountriesService;
    @Autowired
    private FixerService fixerService;
    @Autowired
    private DistanceHelper distanceHelper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IpTracerService.class);

    private final String ipRegex = "^(?=.*[^\\.]$)((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.?){4}$";

    public IpTracerResponse trace(String ip) {
        if (isAValidIp(ip)) {
            LOGGER.info("The IP is valid");
            return processIp(ip);
        } else {
            LOGGER.info("The IP provided is not valid");
            throw new InvalidIpException("The IP provided is not valid");
        }
    }

    private IpTracerResponse processIp(String ip) {
        Ip2CountryResponse ip2CountryResponse = ip2CountryService.getCountry(ip);
        RestCountry restCountry = restCountriesService.getRestCountries(ip2CountryResponse.getCountryCode());
        FixerResponse fixerResponse = fixerService.getCurrencyRate();

        return new IpTracerResponse(
                ip,
                buildDateTime(),
                ip2CountryResponse.getCountryCode(),
                ip2CountryResponse.getCountryName(),
                restCountry.getLanguages(),
                buildCurrency(restCountry.getCurrency(), fixerResponse.getRate()),
                restCountry.getTimes(),
                buildDistance(restCountry.getLat(), restCountry.getLon())
        );
    }

    private boolean isAValidIp(String ip) {
        return ip.matches(ipRegex);
    }

    private String buildDateTime() {
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return LocalDateTime.now().toString(formatter);
    }

    private String buildCurrency(String currency, double rate) {
        return currency + " (1 " + currency + " = " + rate + " USD)";
    }

    private String buildDistance(double ipLat, double ipLong) {
        return distanceHelper.distance(ipLat, ipLong) + " kms";
    }
}
