package org.youcode.trackprocraservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.youcode.trackprocraservice.repository.interfaces.AppUserRepository;

@Component
public class UserValidationUtils {

    @Autowired
    private AppUserRepository appUserRepository;

    public boolean isValidEmail(String email) {
        // Basic email validation (you can use a regex for more robust validation)
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean isEmailUnique(String email) {
        return !appUserRepository.existsByEmail(email); // Check if email is unique
    }

    public boolean isValidUsername(String username) {
        // Username validation (e.g., alphanumeric, underscores, and dots)
        return username != null && username.matches("^[a-zA-Z0-9_.-]{3,50}$");
    }

    public boolean isUsernameUnique(String username) {
        return !appUserRepository.existsByUsername(username); // Check if username is unique
    }

    public boolean isValidPassword(String password) {
        // Password validation (e.g., at least 8 characters, one uppercase, one lowercase, one digit)
        return password != null && password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number validation (e.g., 10 digits)
        return phoneNumber != null && phoneNumber.matches("^\\d{10}$");
    }

    public boolean isValidUserData(String email, String username, String password, String phoneNumber) {
        return isValidEmail(email) &&
                isEmailUnique(email) &&
                isValidUsername(username) &&
                isUsernameUnique(username) &&
                isValidPassword(password) &&
                isValidPhoneNumber(phoneNumber);
    }
}