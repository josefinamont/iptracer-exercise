package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import backend.exercise.iptracer.service.fixer.FixerResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FixerClient extends HttpClient {
    @Value("${fixer.baseUrl}")
    private String baseUrl;
    @Value("${fixer.path}")
    private String path;
    @Value("${fixer.accessKey}")
    private String accessKey;
    @Value("${fixer.symbols}")
    private String symbols;

    public FixerResponse getResponse(String currencyCode) {
        String url = baseUrl + path + "?access_key=" + accessKey + "&symbols=" + symbols + "," + currencyCode;
        String response = super.get(url);

        FixerResponse fixerResponse = super.customMapper.map(response, new TypeReference<FixerResponse>() {});
        return fixerResponse;
    }
}
