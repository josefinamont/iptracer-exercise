package backend.exercise.iptracer.common;

import backend.exercise.iptracer.model.exceptions.UnexpectedResponseStatusException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class HttpConnector {
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnector.class);

    // TODO mejorar esto
    public String get(String url, String ip) {
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return Optional.ofNullable(entity).isPresent() ? EntityUtils.toString(entity) : null;
            } else {
                LOGGER.error("Unexpected response status: " + status);
                throw new RuntimeException(response.getEntity().toString());
            }
        };


        try {
            url = url + ip;
            HttpGet httpget = new HttpGet(url);
            LOGGER.info("Executing request " + httpget.getRequestLine());

            return httpClient.execute(httpget, responseHandler);
        } catch (IOException e) {
            throw new UnexpectedResponseStatusException("Unexpected response status: ", e);
        }
    }
}
