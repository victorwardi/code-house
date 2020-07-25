package run.victor.api.houseofcode.exception;

/**
 * @author Victor Wardi - @victorwardi
 */
public class AuthorNotFoundExcepiton extends RuntimeException {

    public AuthorNotFoundExcepiton() {
        super("No Author found!");
    }
}
