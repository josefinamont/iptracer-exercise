package backend.exercise.iptracer.repository;

import org.assertj.core.util.Maps;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FixerRepositoryTest {
    private final FixerRepository repository = new FixerRepository();

    @Test
    public void testGetRateForCurrency() {
        assertTrue(repository.isEmpty());

        repository.withRates(Maps.newHashMap("AR", 314.0));

        assertEquals(Optional.of(314.0), repository.getRateForCurrency("AR"));
        assertEquals(Optional.empty(), repository.getRateForCurrency("ES"));
    }
}