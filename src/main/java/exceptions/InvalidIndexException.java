package exceptions;

import static data.Constants.INVALID_INDEX;

/**
  * A custom error case for when the user has input an invalid index for operations such as MARK and DELETE
  */
public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(String message) {
        super(message);
    }

    /**
      * @return The error message defined under Constants class
      */    @Override
    public String toString() {
        return INVALID_INDEX;
    }
}
