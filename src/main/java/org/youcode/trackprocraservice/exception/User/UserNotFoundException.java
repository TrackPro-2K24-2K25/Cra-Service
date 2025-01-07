package org.youcode.trackprocraservice.exception.User;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("User not found with ID: " + id);
    }

    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }

    public UserNotFoundException(String username, String message) {
        super("User not found with username: " + username + ". " + message);
    }
}
