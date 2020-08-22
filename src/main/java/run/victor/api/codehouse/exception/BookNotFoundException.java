package run.victor.api.codehouse.exception;

/**
 * @author Victor Wardi - @victorwardi
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("No book found!");
    }
}
