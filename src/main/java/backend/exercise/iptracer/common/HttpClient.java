package backend.exercise.iptracer.common;

import backend.exercise.iptracer.model.exceptions.EmptyResponseException;
import backend.exercise.iptracer.model.exceptions.UnexpectedResponseStatusException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
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
public abstract class HttpClient {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    protected final CustomMapper customMapper = new CustomMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    public String get(String url) {
        try {
            HttpGet httpget = new HttpGet(url);
            LOGGER.info("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse response = httpClient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                Optional<HttpEntity> entity = Optional.ofNullable(response.getEntity());
                if (entity.isPresent())
                    return EntityUtils.toString(entity.get());
                else
                    throw new EmptyResponseException("The response body is empty");
            } else {
                throw new UnexpectedResponseStatusException("Unexpected response status: " + status);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new UnexpectedResponseStatusException(e.getMessage(), e);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}