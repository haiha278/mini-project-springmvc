package lg.cns.miniproject.exception.account;

public class InvalidUsernamePasswordException extends RuntimeException {
    public InvalidUsernamePasswordException(String message) {
        super(message);
    }
}
