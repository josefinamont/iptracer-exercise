package backend.exercise.iptracer.model.exceptions;

import java.io.IOException;

public class UnexpectedResponseStatusException extends RuntimeException {

    public UnexpectedResponseStatusException(String message) {
        super(message);
    }

    public UnexpectedResponseStatusException(String message, IOException e) {
        super(message, e);
    }
}
