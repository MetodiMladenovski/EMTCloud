package mk.ukim.finki.usermanagment.domain.exceptions;


public class FileContentEntityNotFound extends RuntimeException{
    public FileContentEntityNotFound() {
        super("File content entity not found");
    }
}
