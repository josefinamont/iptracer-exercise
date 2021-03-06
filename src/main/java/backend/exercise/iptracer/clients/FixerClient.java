package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import backend.exercise.iptracer.dtos.FixerResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FixerClient extends HttpClient {
    @Value("${fixer.baseUrl}")
    private String baseUrl;
    @Value("${fixer.path}")
    private String path;
    @Value("${fixer.accessKey}")
    private String accessKey;

    public FixerResponse getResponse() {
        String url = baseUrl + path + "?access_key=" + accessKey;
        String response = super.get(url);

        return super.customMapper.map(response, new TypeReference<FixerResponse>() {});
    }
}
