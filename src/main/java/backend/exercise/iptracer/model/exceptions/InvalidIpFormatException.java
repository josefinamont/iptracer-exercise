package backend.exercise.iptracer.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIpFormatException extends RuntimeException {
    public InvalidIpFormatException(String msg) {
        super(msg);
    }
}