package mk.ukim.finki.usermanagment.domain.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){
        super("Wrong password");
    }
}
