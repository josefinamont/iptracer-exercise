package backend.exercise.iptracer.service.fixer;

import backend.exercise.iptracer.clients.FixerClient;
import backend.exercise.iptracer.common.CustomJsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class FixerService {
    private final FixerClient fixerClient;
    private final CustomJsonMapper customJsonMapper;

    public FixerService(FixerClient fixerClient, CustomJsonMapper customJsonMapper) {
        this.fixerClient = fixerClient;
        this.customJsonMapper = customJsonMapper;
    }

    public FixerResponse getCurrencyRate() {
        String response = fixerClient.get();

        return customJsonMapper.fromJson(response, new TypeReference<FixerResponse>() {});
    }
}
