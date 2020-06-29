package backend.exercise.iptracer.model.exceptions;

public class InvalidIpException extends RuntimeException {
    public InvalidIpException(String msg) {
        super(msg);
    }
}