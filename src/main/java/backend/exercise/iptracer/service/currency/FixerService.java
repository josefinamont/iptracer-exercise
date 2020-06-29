package backend.exercise.iptracer.service.currency;

import backend.exercise.iptracer.common.CustomJsonMapper;
import backend.exercise.iptracer.common.HttpConnector;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class FixerService {
    private String url = "http://data.fixer.io/api/latest?access_key=d05624c7ada7d02bfc843ef4d4b07238&symbols=USD";
    private final HttpConnector httpConnector;
    private final CustomJsonMapper customJsonMapper;

    public FixerService(HttpConnector httpConnector, CustomJsonMapper customJsonMapper) {
        this.httpConnector = httpConnector;
        this.customJsonMapper = customJsonMapper;
    }

    public FixerResponse getCurrencyRate() {
        String response = httpConnector.get(url, "");

        return customJsonMapper.fromJson(response, new TypeReference<FixerResponse>() {});
    }
}
