package backend.exercise.iptracer.model.exceptions;

public class InvalidIpFormatException extends RuntimeException {
    public InvalidIpFormatException(String msg) {
        super(msg);
    }
}