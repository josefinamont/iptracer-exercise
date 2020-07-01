package backend.exercise.iptracer.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DistancesTest {

    private Distances distances = new Distances();

    @Test
    public void testUpdateDistances() {
        Double currentDistance = 40.0, initialDistanceValue = 425.0;
        distances.initializeWith(initialDistanceValue);
        distances.updateDistances(currentDistance);

        assertEquals(currentDistance, distances.getNearestDistance());
        assertEquals(425.0, distances.getFurthestDistance());
    }

}