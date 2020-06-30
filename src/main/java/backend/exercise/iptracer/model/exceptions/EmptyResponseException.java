package backend.exercise.iptracer.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmptyResponseException extends RuntimeException {
    public EmptyResponseException(String message) {
        super(message);
    }
}
