package mk.ukim.finki.usermanagment.domain.exceptions;

public class InvalidSessionTokenException extends RuntimeException{
    public InvalidSessionTokenException() {
        super("Invalid Session token");
    }
}
