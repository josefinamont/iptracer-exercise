package backend.exercise.iptracer.model.exceptions;

public class EmptyResponseException extends RuntimeException {
    public EmptyResponseException(String message) {
        super(message);
    }
}
