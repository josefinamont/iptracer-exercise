package backend.exercise.iptracer.clients;

import backend.exercise.iptracer.common.HttpClient;
import org.springframework.stereotype.Component;

@Component
public class FixerClient extends HttpClient {
    private String url = "http://data.fixer.io/api/latest?access_key=d05624c7ada7d02bfc843ef4d4b07238&symbols=USD";

    public String get() {
        return super.get(url);
    }
}
