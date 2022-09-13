package mk.ukim.finki.storagemanagement.domain.exceptions;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException() {
        super("File could not be found");
    }
}
