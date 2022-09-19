package mk.ukim.finki.usermanagment.domain.exceptions;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException() {
        super("Email already in use");
    }
}
