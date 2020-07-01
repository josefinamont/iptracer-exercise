package backend.exercise.iptracer.repository;

import backend.exercise.iptracer.dtos.RestCountry;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RestCountriesRepositoryTest {
    private final RestCountriesRepository repository = new RestCountriesRepository();

    @Test
    public void testGetRateForCurrency() {
        RestCountry restCountry = new RestCountry(
                Lists.newArrayList("Español(es)", "Avañe'ẽ(gn)"),
                "EUR",
                Lists.newArrayList("13:35:17 (UTC-03:00)"),
                -24,
                -36
        );

        assertTrue(repository.isEmpty());

        repository.withCountryInfo("ES", restCountry);

        assertEquals(Optional.of(restCountry), repository.getCountryInfo("ES"));
        assertEquals(Optional.empty(), repository.getCountryInfo("AR"));
    }
}