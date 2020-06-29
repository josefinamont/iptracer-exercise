package backend.exercise.iptracer.service.fixer;

import backend.exercise.iptracer.clients.FixerClient;
import org.springframework.stereotype.Service;

@Service
public class FixerService {
    private final FixerClient fixerClient;

    public FixerService(FixerClient fixerClient) {
        this.fixerClient = fixerClient;
    }

    public FixerResponse getCurrencyRate() {
        return fixerClient.getResponse();
    }
}
