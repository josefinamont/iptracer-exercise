package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.DistanceHelper;
import backend.exercise.iptracer.service.restcountries.RestCountriesResponse;
import backend.exercise.iptracer.service.restcountries.RestCountriesService;
import backend.exercise.iptracer.service.currency.FixerResponse;
import backend.exercise.iptracer.service.currency.FixerService;
import backend.exercise.iptracer.service.ip2country.Ip2CountryResponse;
import backend.exercise.iptracer.service.ip2country.Ip2CountryService;
import org.joda.time.LocalDateTime;
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
    private DistanceHelper distanceHelper;
    @Autowired
    private FixerService fixerService;

    public IpTracerResponse trace(String ip) {
        Ip2CountryResponse geolocalizationResponse = ip2CountryService.getCountry(ip);
        RestCountriesResponse restCountriesResponse = restCountriesService.getCountryInformation(
                geolocalizationResponse.getCountryCode());
        FixerResponse fixerResponse = fixerService.getCurrencyRate();

        IpTracerResponse response = new IpTracerResponse();
        response.setIp(ip);
        response.setIsoCode(geolocalizationResponse.getCountryCode());
        response.setDatetime(LocalDateTime.now());
        response.setCountry(geolocalizationResponse.getCountryName());
        response.setLanguages(restCountriesResponse.getLanguages());
        response.setCurrency(restCountriesResponse.getCurrency() + " (1 " + restCountriesResponse.getCurrency()
                + " = " + fixerResponse.getRate().toString() + " USD)");
        response.setTimes(restCountriesResponse.getTimezones().stream().map(IpTracerService::getUTCdatetimeAsString).collect(Collectors.toList()));
        response.setEstimatedDistance(distanceHelper.distance(restCountriesResponse.getLatlng().get(0),
                restCountriesResponse.getLatlng().get(1)) + " kms");
        return response;
    }

    public static String getUTCdatetimeAsString(String timezone) {
        Integer hours = timezone.split("\\+").length > 1 ? Integer.parseInt(timezone.split("\\+")[1].split(":")[0]) : 0;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String zdt = ZonedDateTime.now(UTC.toTimeZone().toZoneId()).plusHours(hours).format(sdf);

        return zdt + " (" + timezone + ")"; // TODO corregir
    }
}
