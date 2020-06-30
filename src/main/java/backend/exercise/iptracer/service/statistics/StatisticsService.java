package backend.exercise.iptracer.service.statistics;

import backend.exercise.iptracer.service.iptracer.DistancesResponse;
import backend.exercise.iptracer.service.iptracer.IpTracerResponse;
import backend.exercise.iptracer.service.iptracer.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository repository;

    public DistancesResponse distances() {
        return repository.distances();
    }

    public void updateStatistics(IpTracerResponse response) {
        repository.insertStatistic(
                response.getIsoCode(),
                Double.parseDouble(response.getEstimatedDistance().split(" ")[0])
        );
    }
}
