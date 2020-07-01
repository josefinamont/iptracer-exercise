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
import backend.exercise.iptracer.dtos.RestCountry;
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
    private final String USD_CURRENCY_CODE = "USD";

    private static final Logger LOGGER = LoggerFactory.getLogger(IpTracerService.class);

    private final String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public IpTracerResponse trace(String ip) {
        if (isAValidIp(ip)) {
            LOGGER.info("The IP provided is valid");
            return processIp(ip);
        } else {
            LOGGER.info("The IP provided is not valid");
            throw new InvalidIpFormatException("The IP provided is not valid");
        }
    }

    private IpTracerResponse processIp(String ip) {
        Ip2CountryResponse ip2CountryResponse = ip2CountryService.getCountry(ip);
        RestCountry restCountry = restCountriesService.getRestCountries(ip2CountryResponse.getCountryCode());
        Double currencyRate = fixerService.getCurrencyRate(restCountry.getCurrency());
        Double usdRate = fixerService.getCurrencyRate(USD_CURRENCY_CODE);

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
                buildCurrency(restCountry.getCurrency(), currencyRate, usdRate),
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

    private String buildCurrency(String currency, Double currencyRate, Double usdRate) {
        double conversionFromCurrencyToUSD = usdRate / currencyRate;
        return currency + " (1 " + currency + " = " + conversionFromCurrencyToUSD + " USD)";
    }

    private String buildDistance(double estimatedDistance) {
        return estimatedDistance + " kms";
    }
}
