package backend.exercise.iptracer.repository;

import backend.exercise.iptracer.dtos.DistancesResponse;
import backend.exercise.iptracer.dtos.Statistic;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatisticsRepository {
    private static final ConcurrentHashMap<String, Statistic> statsPerCountry = new ConcurrentHashMap<>();
    private static final DistancesResponse distances = new DistancesResponse();

    public static void insertStatistic(String country, double distance) {
        if (statsPerCountry.isEmpty()) {
            distances.setNearestDistance(distance);
            distances.setFurthestDistance(distance);
            distances.setAverageDistance(distance);
            statsPerCountry.put(country, new Statistic(distance, 1));
        } else {
            Optional<Statistic> stat = Optional.ofNullable(statsPerCountry.get(country));

            if (stat.isPresent()) {
                stat.get().incrementInvocations();
                stat.get().withDistance(distance);
            } else {
                statsPerCountry.put(country, new Statistic(distance, 1));
            }

            if (distance < distances.getNearestDistance()) distances.setNearestDistance(distance);
            if (distance > distances.getFurthestDistance()) distances.setFurthestDistance(distance);

            distances.setAverageDistance(averageDistance());
        }
    }

    public DistancesResponse distances() {
        return distances;
    }

    private static Double averageDistance() {
        Double sum = statsPerCountry.values().stream()
                .map(stat -> stat.getDistance() * stat.getInvocations())
                .mapToDouble(Double::doubleValue)
                .sum();

        Integer totalInvocations = statsPerCountry.values().stream()
                .mapToInt(Statistic::getInvocations)
                .sum();

        return (double) Math.round(sum / totalInvocations);
    }
}
