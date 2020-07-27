package run.victor.api.codehouse.exception;

/**
 * @author Victor Wardi - @victorwardi
 */
public class AuthorNotFoundExcepiton extends RuntimeException {

    public AuthorNotFoundExcepiton() {
        super("No Author found!");
    }
}
