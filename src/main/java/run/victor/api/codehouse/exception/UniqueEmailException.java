package run.victor.api.codehouse.exception;

/**
 * @author Victor Wardi - @victorwardi
 */
public class UniqueEmailException extends RuntimeException {

    public UniqueEmailException() {
        super("Email must be unique!");
    }
}
