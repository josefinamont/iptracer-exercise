package backend.exercise.iptracer.service.currency;

import backend.exercise.iptracer.common.CustomJsonMapper;
import backend.exercise.iptracer.common.HttpConnector;
import com.fasterxml.jackson.core.type.TypeReference;

public class CurrencyService {
    private String url = "http://data.fixer.io/api/latest?access_key=d05624c7ada7d02bfc843ef4d4b07238&symbols=";
    private final HttpConnector httpConnector;
    private final CustomJsonMapper customJsonMapper;

    public CurrencyService(HttpConnector httpConnector, CustomJsonMapper customJsonMapper) {
        this.httpConnector = httpConnector;
        this.customJsonMapper = customJsonMapper;
    }

    public CurrencyResponse getCurrencyRate(String currencyCode) {
        String response = httpConnector.get(url, currencyCode);

        return customJsonMapper.fromJson(response, new TypeReference<CurrencyResponse>() {});
    }
}
