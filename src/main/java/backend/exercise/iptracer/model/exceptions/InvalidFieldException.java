package backend.exercise.iptracer.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message, NullPointerException e) {
        super(message, e);
    }
}
