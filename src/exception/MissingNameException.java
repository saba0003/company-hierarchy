package exception;

public class MissingNameException extends RuntimeException {

    public MissingNameException(String message) {
        super(message);
    }
}
