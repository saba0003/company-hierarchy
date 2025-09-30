package exception;

public class MissingBirthDateException extends RuntimeException {

    public MissingBirthDateException(String message) {
        super(message);
    }
}
