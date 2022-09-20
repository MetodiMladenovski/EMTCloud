package mk.ukim.finki.usermanagment.domain.exceptions;

public class FileFailedToStore extends RuntimeException{
    public FileFailedToStore() {
        super("File failed to store due to IO Exception");
    }
}
