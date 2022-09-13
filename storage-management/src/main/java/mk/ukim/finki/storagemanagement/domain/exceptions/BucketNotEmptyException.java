package mk.ukim.finki.storagemanagement.domain.exceptions;

public class BucketNotEmptyException extends RuntimeException{
    public BucketNotEmptyException() {
        super("Bucket is not empty");
    }
}
