package backend.exercise.iptracer.repository;

import backend.exercise.iptracer.dtos.Distances;
import backend.exercise.iptracer.dtos.Statistic;
import backend.exercise.iptracer.helpers.DistanceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatisticsRepository {
    private static final ConcurrentHashMap<String, Statistic> statsPerCountry = new ConcurrentHashMap<>();
    private static final Distances distances = new Distances();
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsRepository.class);

    public static void insertStatistic(String country, double distance) {
        LOGGER.info("Inserting or updating statistic");

        if (statsPerCountry.isEmpty()) {
            distances.initializeWith(distance);
            statsPerCountry.put(country, new Statistic(distance, 1));
        } else {
            Statistic currentStat = statsPerCountry.getOrDefault(country, new Statistic());
            currentStat.incrementInvocations();
            currentStat.withDistance(distance);

            statsPerCountry.put(country, currentStat);
            distances.updateDistances(distance);
            distances.setAverageDistance(averageDistance());
        }
    }

    private static Double averageDistance() {
        LOGGER.info("Calculating average distance");

        Double sum = statsPerCountry.values().stream()
                .map(stat -> stat.getDistance() * stat.getInvocations())
                .mapToDouble(Double::doubleValue)
                .sum();

        Integer totalInvocations = statsPerCountry.values().stream()
                .mapToInt(Statistic::getInvocations)
                .sum();

        return (double) Math.round(sum / totalInvocations);
    }

    public Distances distances() {
        return distances;
    }
}
