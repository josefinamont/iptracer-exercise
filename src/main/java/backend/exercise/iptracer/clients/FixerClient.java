package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
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
    @Value("${fixer.symbols}")
    private String symbols;

    public String get() {
        String url = baseUrl + path + "?access_key=" + accessKey + "&symbols=" + symbols;
        return super.get(url);
    }
}
