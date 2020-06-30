package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.dtos.IpTracerResponse;
import backend.exercise.iptracer.model.exceptions.InvalidIpFormatException;
import backend.exercise.iptracer.helpers.DistanceHelper;
import backend.exercise.iptracer.dtos.FixerResponse;
import backend.exercise.iptracer.repository.StatisticsRepository;
import backend.exercise.iptracer.service.fixer.FixerService;
import backend.exercise.iptracer.dtos.Ip2CountryResponse;
import backend.exercise.iptracer.service.ip2country.Ip2CountryService;
import backend.exercise.iptracer.service.restcountries.RestCountriesService;
import backend.exercise.iptracer.service.restcountries.RestCountry;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    private final String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public IpTracerResponse trace(String ip) {
        if (isAValidIp(ip)) {
            LOGGER.info("The IP is valid");
            return processIp(ip);
        } else {
            LOGGER.info("The IP provided is not valid");
            throw new InvalidIpFormatException("The IP provided is not valid");
        }
    }

    private IpTracerResponse processIp(String ip) {
        Ip2CountryResponse ip2CountryResponse = ip2CountryService.getCountry(ip);
        RestCountry restCountry = restCountriesService.getRestCountries(ip2CountryResponse.getCountryCode());
        FixerResponse fixerResponse = fixerService.getCurrencyRate(restCountry.getCurrency()); // TODO

        double estimatedDistance = distanceHelper.distance(restCountry.getLat(), restCountry.getLon());

        StatisticsRepository.insertStatistic(
                ip2CountryResponse.getCountryCode(),
                estimatedDistance);

        return new IpTracerResponse(
                ip,
                buildDateTime(),
                ip2CountryResponse.getCountryName(),
                ip2CountryResponse.getCountryCode(),
                restCountry.getLanguages(),
                buildCurrency(restCountry.getCurrency(), fixerResponse.getRates()),
                restCountry.getTimes(),
                buildDistance(estimatedDistance)
        );
    }

    private boolean isAValidIp(String ip) {
        return ip.matches(ipRegex);
    }

    private String buildDateTime() {
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return LocalDateTime.now().toString(formatter);
    }

    private String buildCurrency(String currency, Map<String,Double> rates) {
        double conversionFromCurrencyToUSD = rates.getOrDefault("USD", 0.0) / rates.getOrDefault(currency, 1.0);
        return currency + " (1 " + currency + " = " + conversionFromCurrencyToUSD + " USD)";
    }

    private String buildDistance(double estimatedDistance) {
        return estimatedDistance + " kms";
    }
}
