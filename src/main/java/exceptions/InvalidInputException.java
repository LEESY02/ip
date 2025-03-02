package exceptions;

import static data.Constants.INVALID_SEARCH_INPUT;

/*
 * A custom error case for when the user has input an empty string as the SEARCH keyword
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

    /*
     * @returns The error message defined under Constants class
     */
    @Override
    public String toString() {
        return INVALID_SEARCH_INPUT;
    }
}
