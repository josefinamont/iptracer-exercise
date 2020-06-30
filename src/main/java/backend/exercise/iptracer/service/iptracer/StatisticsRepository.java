package backend.exercise.iptracer.service.iptracer;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatisticsRepository {
    private ConcurrentHashMap<String, Statistic> statsPerCountry = new ConcurrentHashMap<String, Statistic>();
    private DistancesResponse distances = new DistancesResponse();

    public void insertStatistic(String country, double distance) {
        Optional<Statistic> stat = Optional.ofNullable(statsPerCountry.get(country));

        if (statsPerCountry.isEmpty()) {
            distances.setNearestDistance(distance);
            distances.setFurthestDistance(distance);
            distances.setAverageDistance(distance);
            statsPerCountry.put(country, new Statistic(distance, 1));
        } else {
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

    private Double averageDistance() {
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
