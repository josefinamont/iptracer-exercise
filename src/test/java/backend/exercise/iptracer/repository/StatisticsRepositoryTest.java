package backend.exercise.iptracer.repository;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StatisticsRepositoryTest {
    private final StatisticsRepository repository = new StatisticsRepository();

    @Test
    public void testInsertStatistic() {
        StatisticsRepository.insertStatistic("ES", 14000);
        StatisticsRepository.insertStatistic("ES", 14000);
        StatisticsRepository.insertStatistic("AR", 518);
        StatisticsRepository.insertStatistic("UY", 340);
        StatisticsRepository.insertStatistic("UY", 340);
        StatisticsRepository.insertStatistic("UY", 340);

        assertEquals(2, repository.getStatisticBy("ES").getInvocations());
        assertEquals(14000, repository.getStatisticBy("ES").getDistance());

        assertEquals(1, repository.getStatisticBy("AR").getInvocations());
        assertEquals(518, repository.getStatisticBy("AR").getDistance());

        assertEquals(3, repository.getStatisticBy("UY").getInvocations());
        assertEquals(340, repository.getStatisticBy("UY").getDistance());

        assertEquals(0, repository.getStatisticBy("BR").getInvocations());
        assertEquals(0, repository.getStatisticBy("BR").getDistance());

        assertEquals(340, repository.distances().getNearestDistance());
        assertEquals(14000, repository.distances().getFurthestDistance());
        assertEquals(4923, repository.distances().getAverageDistance());
    }

}