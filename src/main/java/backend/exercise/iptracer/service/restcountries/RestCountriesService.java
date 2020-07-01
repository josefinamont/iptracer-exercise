package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.RestCountriesClient;
import backend.exercise.iptracer.dtos.Language;
import backend.exercise.iptracer.dtos.RestCountriesResponse;
import backend.exercise.iptracer.dtos.RestCountry;
import backend.exercise.iptracer.model.exceptions.InvalidFieldException;
import backend.exercise.iptracer.repository.RestCountriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.joda.time.DateTimeZone.UTC;

@Service
public class RestCountriesService {
    private final RestCountriesClient restCountriesClient;
    @Autowired
    private RestCountriesRepository restCountriesRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final Logger LOGGER = LoggerFactory.getLogger(RestCountriesService.class);

    public RestCountriesService(RestCountriesClient restCountriesClient) {
        this.restCountriesClient = restCountriesClient;
    }

    public RestCountry getRestCountries(String countryCode) {
        Optional<RestCountry> possibleCountryInfo = restCountriesRepository.getCountryInfo(countryCode);

        if (!possibleCountryInfo.isPresent()) {
            LOGGER.info("Updating the local repository");
            RestCountriesResponse response = restCountriesClient.getResponse(countryCode);
            RestCountry restCountry = buildRestCountry(response);
            restCountriesRepository.withCountryInfo(countryCode, restCountry);

            return restCountry;
        } else {
            LOGGER.info("Found " + countryCode + " info in the repository");
            return possibleCountryInfo.get();
        }
    }

    private RestCountry buildRestCountry(RestCountriesResponse response) {
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
