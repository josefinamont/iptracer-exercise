package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.DistanceHelper;
import backend.exercise.iptracer.service.country.CountryInformationResponse;
import backend.exercise.iptracer.service.country.CountryInformationService;
import backend.exercise.iptracer.service.currency.CurrencyResponse;
import backend.exercise.iptracer.service.currency.CurrencyService;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationResponse;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.joda.time.DateTimeZone.UTC;

@Service
public class IpTracerService {
    @Autowired
    private IpGeolocalizationService geolocalizationService;
    @Autowired
    private CountryInformationService countryInformationService;
    @Autowired
    private DistanceHelper distanceHelper;
    @Autowired
    private CurrencyService currencyService;

    public IpTracerResponse trace(String ip) {
        IpGeolocalizationResponse geolocalizationResponse = geolocalizationService.getIpGeolocalization(ip);
        CountryInformationResponse countryInformationResponse = countryInformationService.getCountryInformation(
                geolocalizationResponse.getCountryCode());
        CurrencyResponse currencyResponse = currencyService.getCurrencyRate();

        IpTracerResponse response = new IpTracerResponse();
        response.setIp(ip);
        response.setIsoCode(geolocalizationResponse.getCountryCode());
        response.setDatetime(LocalDateTime.now());
        response.setCountry(geolocalizationResponse.getCountryName());
        response.setLanguages(countryInformationResponse.getLanguages());
        response.setCurrency(countryInformationResponse.getCurrency() + " (1 " + countryInformationResponse.getCurrency()
                + " = " + currencyResponse.getRate().toString() + " USD)");
        response.setTimes(countryInformationResponse.getTimezones().stream().map(IpTracerService::getUTCdatetimeAsString).collect(Collectors.toList()));
        response.setEstimatedDistance(distanceHelper.distance(countryInformationResponse.getLatlng().get(0),
                countryInformationResponse.getLatlng().get(1)) + " kms");
        return response;
    }

    public static String getUTCdatetimeAsString(String timezone) {
        Integer hours = timezone.split("\\+").length > 1 ? Integer.parseInt(timezone.split("\\+")[1].split(":")[0]) : 0;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String zdt = ZonedDateTime.now(UTC.toTimeZone().toZoneId()).plusHours(hours).format(sdf);

        return zdt + " (" + timezone + ")"; // TODO corregir
    }
}
