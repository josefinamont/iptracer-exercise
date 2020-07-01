package backend.exercise.iptracer.service.restcountries;

import backend.exercise.iptracer.clients.FixerClient;
import backend.exercise.iptracer.clients.RestCountriesClient;
import backend.exercise.iptracer.dtos.FixerResponse;
import backend.exercise.iptracer.dtos.RestCountriesResponse;
import backend.exercise.iptracer.dtos.RestCountry;
import backend.exercise.iptracer.repository.FixerRepository;
import backend.exercise.iptracer.repository.RestCountriesRepository;
import backend.exercise.iptracer.service.fixer.FixerService;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestCountriesServiceTest {
    @InjectMocks
    private RestCountriesService restCountriesService;
    @Mock
    private RestCountriesClient restCountriesClient;
    @Mock
    private RestCountriesRepository restCountriesRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRestCountriesGoingToRestCountries() {
        RestCountry expectedRestCountry = new RestCountry(
                Lists.newArrayList("Español(es)"),
                "ARS",
                Lists.newArrayList("13:35:17 (UTC-03:00)"),
                -24,
                -36
        );
        when(restCountriesRepository.getCountryInfo("AR")).thenReturn(Optional.empty());
        when(restCountriesClient.getResponse("AR")).thenReturn(new RestCountriesResponse()); // Shouldn't be empty
        when(restCountriesRepository.getCountryInfo("AR")).thenReturn(Optional.of(expectedRestCountry));

        RestCountry actualRestCountry = restCountriesService.getRestCountries("AR");

        assertEquals(expectedRestCountry, actualRestCountry);
    }

    @Test
    public void testGetCurrencyRateFromRepository() {
        RestCountry expectedRestCountry = new RestCountry(
                Lists.newArrayList("Español(es)"),
                "ARS",
                Lists.newArrayList("13:35:17 (UTC-03:00)"),
                -24,
                -36
        );
        when(restCountriesRepository.getCountryInfo("AR")).thenReturn(Optional.of(expectedRestCountry));

        RestCountry actualRestCountry = restCountriesService.getRestCountries("AR");

        assertEquals(expectedRestCountry, actualRestCountry);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCurrencyRateNotFindingRate() {
        when(restCountriesRepository.getCountryInfo("AR")).thenReturn(Optional.empty());
        when(restCountriesClient.getResponse("AR")).thenThrow(new RuntimeException());

        restCountriesService.getRestCountries("AR");
    }
}