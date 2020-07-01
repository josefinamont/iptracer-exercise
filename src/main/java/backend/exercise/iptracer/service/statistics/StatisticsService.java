package backend.exercise.iptracer.service.statistics;

import backend.exercise.iptracer.model.Distances;
import backend.exercise.iptracer.repository.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    public Distances distances() {
        LOGGER.info("Nearest distance: " + repository.distances().getNearestDistance());
        LOGGER.info("Furthest distance: " + repository.distances().getFurthestDistance());
        LOGGER.info("Average distance: " + repository.distances().getAverageDistance());

        return repository.distances();
    }
}
