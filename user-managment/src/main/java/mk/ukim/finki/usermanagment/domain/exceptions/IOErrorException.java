package mk.ukim.finki.usermanagment.domain.exceptions;

public class IOErrorException extends RuntimeException{
    public IOErrorException(){
        super("IO Error occurred");
    }
}
