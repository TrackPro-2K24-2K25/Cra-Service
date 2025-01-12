package org.youcode.trackprocraservice.repository.interfaces.user;

import org.youcode.trackprocraservice.domain.entities.AppUser;

import java.util.UUID;

public interface PasswordManagementRepository {
    AppUser updateUserPassword(UUID userId, String newPassword);
    boolean verifyPassword(UUID userId, String rawPassword);
}
