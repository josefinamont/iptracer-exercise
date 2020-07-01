package backend.exercise.iptracer.service.fixer;

import backend.exercise.iptracer.clients.FixerClient;
import backend.exercise.iptracer.dtos.FixerResponse;
import backend.exercise.iptracer.repository.FixerRepository;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FixerServiceTest {
    @InjectMocks
    private FixerService fixerService;
    @Mock
    private FixerClient fixerClient;
    @Mock
    private FixerRepository fixerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCurrencyRateGoingToFixer() {
        Double expectedRate = 314.0;
        when(fixerRepository.getRateForCurrency("AR")).thenReturn(Optional.empty());
        when(fixerClient.getResponse()).thenReturn(new FixerResponse(Maps.newHashMap("AR", expectedRate)));
        when(fixerRepository.getRateForCurrency("AR")).thenReturn(Optional.of(314.0));

        Double actualRate = fixerService.getCurrencyRate("AR");

        assertEquals(expectedRate, actualRate);
    }

    @Test
    public void testGetCurrencyRateFromRepository() {
        Double expectedRate = 100.0;
        when(fixerRepository.getRateForCurrency("AR")).thenReturn(Optional.of(expectedRate));

        Double actualRate = fixerService.getCurrencyRate("AR");

        assertEquals(expectedRate, actualRate);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCurrencyRateNotFindingRate() {
        when(fixerRepository.getRateForCurrency("AR")).thenReturn(Optional.empty());
        when(fixerClient.getResponse()).thenThrow(new RuntimeException());

        fixerService.getCurrencyRate("AR");
    }
}