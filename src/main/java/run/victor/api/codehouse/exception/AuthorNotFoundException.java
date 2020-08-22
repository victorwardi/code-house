package run.victor.api.codehouse.exception;

/**
 * @author Victor Wardi - @victorwardi
 */
public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException() {
        super("No Author found!");
    }
}
