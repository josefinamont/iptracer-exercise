package backend.exercise.iptracer.service.fixer;

import backend.exercise.iptracer.clients.FixerClient;
import backend.exercise.iptracer.common.CustomMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class FixerService {
    private final FixerClient fixerClient;
    private final CustomMapper customMapper;

    public FixerService(FixerClient fixerClient, CustomMapper customMapper) {
        this.fixerClient = fixerClient;
        this.customMapper = customMapper;
    }

    public FixerResponse getCurrencyRate() {
        String response = fixerClient.get();

        return customMapper.parse(response, new TypeReference<FixerResponse>() {});
    }
}
