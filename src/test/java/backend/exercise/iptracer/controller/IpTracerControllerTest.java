package backend.exercise.iptracer.controller;

import backend.exercise.iptracer.common.CustomMapper;
import backend.exercise.iptracer.dtos.IpDataDto;
import backend.exercise.iptracer.dtos.IpTracerResponse;
import backend.exercise.iptracer.model.Distances;
import backend.exercise.iptracer.service.iptracer.IpTracerService;
import backend.exercise.iptracer.service.statistics.StatisticsService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IpTracerControllerTest {
    @InjectMocks
    private IpTracerController controller;
    @Mock
    IpTracerService ipTracerService;
    @Mock
    StatisticsService statisticsService;

    CustomMapper mapper = new CustomMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGreetingsEndpoint() {
        assertEquals("Hello World!", controller.greetings());
    }

    @Test
    public void testStatsEndpoint() {
        when(statisticsService.distances()).thenReturn(new Distances());

        assertEquals(0, controller.stats().getNearestDistance());
        assertEquals(0, controller.stats().getFurthestDistance());
        assertEquals(0, controller.stats().getAverageDistance());

        Distances distances = new Distances();
        distances.setNearestDistance(310);
        distances.setFurthestDistance(14000);
        distances.setAverageDistance(7155);

        when(statisticsService.distances()).thenReturn(distances);

        assertEquals(310, controller.stats().getNearestDistance());
        assertEquals(14000, controller.stats().getFurthestDistance());
        assertEquals(7155, controller.stats().getAverageDistance());
    }

    @Test
    public void testTraceEndpoint() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("trace-response.json");
        IpTracerResponse response = mapper.map(new InputStreamReader(inputStream), new TypeReference<IpTracerResponse>(){});

        when(ipTracerService.trace(any())).thenReturn(response);
        IpTracerResponse controllerResponse = controller.trace(new IpDataDto("83.44.196.93"));

        assertEquals("83.44.196.93", controllerResponse.getIp());
        assertEquals("2020-06-30T21:08:07.148", controllerResponse.getDatetime());
        assertEquals("Spain", controllerResponse.getCountry());
        assertEquals("ES", controllerResponse.getIsoCode());
        assertTrue(controllerResponse.getLanguages().contains("Español(es)"));
        assertTrue(controllerResponse.getTimes().contains("21:08:06 (UTC)"));
        assertTrue(controllerResponse.getTimes().contains("22:08:06 (UTC+01:00)"));
        assertEquals("9998.0 kms", controllerResponse.getEstimatedDistance());

    }
}