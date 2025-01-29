package algorithms;

public class IteratorOutOfBoundsException extends RuntimeException {

    public IteratorOutOfBoundsException() {
    }

    public IteratorOutOfBoundsException(String message) {
        super(message);
    }

    public IteratorOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
