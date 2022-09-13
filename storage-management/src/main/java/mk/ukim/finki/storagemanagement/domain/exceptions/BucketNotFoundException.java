package mk.ukim.finki.storagemanagement.domain.exceptions;

public class BucketNotFoundException extends RuntimeException{
    public BucketNotFoundException() {
        super("Bucket with the given id was not found");
    }
}
