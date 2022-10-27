package com.flight.overall.exception;

public class ProfileAlreadyExistException extends RuntimeException {

    public ProfileAlreadyExistException() {
        super();
    }

    public ProfileAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileAlreadyExistException(String message) {
        super(message);
    }

    public ProfileAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
