package com.flight.overall.exception;

public class ProfileAlreadyExistException extends RuntimeException {

    public ProfileAlreadyExistException() {
        super();
    }

    public ProfileAlreadyExistException(String username, Throwable cause) {
        super("Profile with username " + username + " already exists", cause);
    }

    public ProfileAlreadyExistException(String username) {
        super("Profile with username " + username + " already exists");
    }

    public ProfileAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
