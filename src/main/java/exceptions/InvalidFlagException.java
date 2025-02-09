package exceptions;

import java.util.NoSuchElementException;

public class InvalidFlagException extends RuntimeException {
    public InvalidFlagException (String str) {
        super(str);
    }
}
