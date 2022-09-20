package mk.ukim.finki.usermanagment.domain.exceptions;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException() {
        super("File could not be found");
    }
}
