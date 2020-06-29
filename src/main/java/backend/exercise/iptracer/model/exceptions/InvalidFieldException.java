package backend.exercise.iptracer.model.exceptions;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message, NullPointerException e) {
        super(message, e);
    }
}
