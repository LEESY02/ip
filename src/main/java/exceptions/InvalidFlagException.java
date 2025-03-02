package exceptions;

import static data.Constants.TAG_ERROR;

/*
 * A custom error case for when the user has input an invalid flag (tag) for Event and Deadline tasks
 */
public class InvalidFlagException extends RuntimeException {
    public InvalidFlagException (String str) {
        super(str);
    }

    /*
     * @returns The error message defined under Constants class
     */
    @Override
    public String toString() {
        return TAG_ERROR;
    }
}
