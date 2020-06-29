package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.DistanceHelper;
import backend.exercise.iptracer.service.restcountries.RestCountriesResponse;
import backend.exercise.iptracer.service.restcountries.RestCountriesService;
import backend.exercise.iptracer.service.fixer.FixerResponse;
import backend.exercise.iptracer.service.fixer.FixerService;
import backend.exercise.iptracer.service.ip2country.Ip2CountryResponse;
import backend.exercise.iptracer.service.ip2country.Ip2CountryService;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static org.joda.time.DateTimeZone.UTC;

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

    public IpTracerResponse trace(String ip) {
        Ip2CountryResponse ip2CountryResponse = ip2CountryService.getCountry(ip);
        RestCountriesResponse restCountriesResponse = restCountriesService.getRestCountries(ip2CountryResponse.getCountryCode());
        FixerResponse fixerResponse = fixerService.getCurrencyRate();

        IpTracerResponse response = new IpTracerResponse();
        response.setIp(ip);
        response.setIsoCode(ip2CountryResponse.getCountryCode());
        response.setDatetime(buildDateTime(LocalDateTime.now()));
        response.setCountry(ip2CountryResponse.getCountryName());
        response.setLanguages(restCountriesResponse.getLanguages());
        response.setCurrency(buildCurrency(restCountriesResponse.getCurrency(), fixerResponse.getRate()));

        response.setTimes(restCountriesResponse.getTimezones().stream().map(IpTracerService::getUTCdatetimeAsString).collect(Collectors.toList()));
        response.setEstimatedDistance(distanceHelper.distance(restCountriesResponse.getLatlng().get(0),
                restCountriesResponse.getLatlng().get(1)) + " kms");
        return response;
    }

    private static String getUTCdatetimeAsString(String timezone) {
        Integer hours = timezone.split("\\+").length > 1 ? Integer.parseInt(timezone.split("\\+")[1].split(":")[0]) : 0;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String zdt = ZonedDateTime.now(UTC.toTimeZone().toZoneId()).plusHours(hours).format(sdf);

        return zdt + " (" + timezone + ")"; // TODO corregir
    }

    private String buildDateTime(LocalDateTime date) {
        org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return date.toString(fmt);
    }

    private String buildCurrency(String currency, double rate) {
        return currency + " (1 " + currency + " = " + rate + " USD)";
    }
}
