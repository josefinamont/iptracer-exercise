package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.dtos.IpTracerResponse;
import backend.exercise.iptracer.model.exceptions.InvalidIpFormatException;
import backend.exercise.iptracer.helpers.DistanceHelper;
import backend.exercise.iptracer.dtos.FixerResponse;
import backend.exercise.iptracer.service.fixer.FixerService;
import backend.exercise.iptracer.dtos.Ip2CountryResponse;
import backend.exercise.iptracer.service.ip2country.Ip2CountryService;
import backend.exercise.iptracer.service.restcountries.RestCountriesService;
import backend.exercise.iptracer.service.restcountries.RestCountry;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IpTracerServiceTest {
    @InjectMocks
    private IpTracerService service;
    @Mock
    private Ip2CountryService ip2CountryService;
    @Mock
    private RestCountriesService restCountriesService;
    @Mock
    private FixerService fixerService;
    @Mock
    private DistanceHelper distanceHelper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidIpFormatException.class)
    public void testInvalidIp() {
        service.trace("81.184");
    }

    @Test
    public void testValidIp() {
        Ip2CountryResponse ip2CountryResponse = new Ip2CountryResponse();
        ip2CountryResponse.setCountryCode("AR");
        ip2CountryResponse.setCountryName("Argentina");

        RestCountry restCountry = new RestCountry(
                Lists.newArrayList("Español(es)"),
                "ARS",
                Lists.newArrayList("14:21:35 (UTC-03:00)"),
                -234,
                2345
        );

        Map<String, Double> rates = Maps.newHashMap("USD", 1.123077);
        rates.put("ARS", 79.061729);

        FixerResponse fixerResponse = new FixerResponse(rates);

        when(ip2CountryService.getCountry(any())).thenReturn(ip2CountryResponse);
        when(restCountriesService.getRestCountries(any())).thenReturn(restCountry);
        when(fixerService.getCurrencyRate()).thenReturn(fixerResponse);
        when(distanceHelper.distance(4.0, -1.0)).thenReturn(3000.0);

        IpTracerResponse response = service.trace("181.46.143.99");

        assertEquals("181.46.143.99", response.getIp());
        assertEquals("Argentina", response.getCountry());
        assertEquals("AR", response.getIsoCode());
        assertTrue(response.getCurrency().contains("ARS"));
        assertTrue(response.getLanguages().contains("Español(es)"));
    }
}