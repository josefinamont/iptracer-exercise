package backend.exercise.iptracer.service.statistics;

import backend.exercise.iptracer.dtos.DistancesResponse;
import backend.exercise.iptracer.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository repository;

    public DistancesResponse distances() {
        return repository.distances();
    }
}
