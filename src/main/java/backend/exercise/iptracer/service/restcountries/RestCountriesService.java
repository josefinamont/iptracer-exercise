package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.RestCountriesClient;
import backend.exercise.iptracer.model.exceptions.InvalidFieldException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static org.joda.time.DateTimeZone.UTC;

@Service
public class RestCountriesService {
    private final RestCountriesClient restCountriesClient;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public RestCountriesService(RestCountriesClient restCountriesClient) {
        this.restCountriesClient = restCountriesClient;
    }

    @Async
    public RestCountry getRestCountries(String countryCode) {
        RestCountriesResponse response = restCountriesClient.getResponse(countryCode);
        double lat, lon;
        String currencyCode;

        try {
            lat = response.getLatlng().get(0);
            lon = response.getLatlng().get(1);
        } catch (NullPointerException e) {
            throw new InvalidFieldException("Could not get lat and lon of the introduced ip", e);
        }

        try {
            currencyCode = response.getCurrencies().get(0).getCode();
        } catch (NullPointerException e) {
            throw new InvalidFieldException("Could not get currency code", e);
        }

        return new RestCountry(
                response.getLanguages().stream().map(this::buildLanguage).collect(Collectors.toList()),
                currencyCode,
                response.getTimezones().stream().map(this::getUTCdatetimeAsString).collect(Collectors.toList()),
                lat,
                lon
        );
    }

    private String getUTCdatetimeAsString(String timezone) {
        int plusHours = timezone.split("\\+").length > 1 ? Integer.parseInt(timezone.split("\\+")[1].split(":")[0]) : 0;
        int minusHours = timezone.split("-").length > 1 ? Integer.parseInt(timezone.split("-")[1].split(":")[0]) : 0;

        String zonedDateTime = ZonedDateTime
                .now(UTC.toTimeZone().toZoneId())
                .plusHours(plusHours)
                .minusHours(minusHours)
                .format(formatter);

        return zonedDateTime + " (" + timezone + ")";
    }

    public String buildLanguage(Language language) {
        return language.getNativeName() + "(" + language.getCode() + ")";
    }
}
