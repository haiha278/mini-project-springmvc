package lg.cns.miniproject.exception.employee;

public class InvalidEmailFormatException extends RuntimeException{
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
