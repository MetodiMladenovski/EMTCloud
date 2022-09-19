package mk.ukim.finki.usermanagment.domain.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User was not found");
    }
}
