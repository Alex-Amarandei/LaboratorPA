package optional.exceptions;

public class NoUserLoggedInException extends Exception {
    public NoUserLoggedInException(String message) {
        super(message);
    }
}
