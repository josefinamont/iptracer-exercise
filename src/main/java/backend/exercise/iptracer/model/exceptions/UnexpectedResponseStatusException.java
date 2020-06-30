package backend.exercise.iptracer.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedResponseStatusException extends RuntimeException {

    public UnexpectedResponseStatusException(String message) {
        super(message);
    }

    public UnexpectedResponseStatusException(String message, IOException e) {
        super(message, e);
    }
}
