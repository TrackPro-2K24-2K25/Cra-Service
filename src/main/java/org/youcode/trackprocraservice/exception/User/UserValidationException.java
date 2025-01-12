package org.youcode.trackprocraservice.exception.User;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String message) {
        super(message);
    }
}