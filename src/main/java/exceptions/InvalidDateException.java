package exceptions;

import static data.Constants.INVALID_DATE;

/*
 * A custom error case for when the user has input a wrong String to define a LocalDate object (not 'YYYY-MM-DD')
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }

    /*
     * @returns The error message defined under Constants class
     */
    @Override
    public String toString() {
        return INVALID_DATE;
    }
}
