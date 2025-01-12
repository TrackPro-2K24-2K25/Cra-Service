package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.trackprocraservice.config.HashPassword;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    @HashPassword
    AppUser createUser(AppUser user);

    Optional<AppUser> getUserById(UUID userId);

    Page<AppUser> getAllUsers(Pageable pageable);

    @HashPassword
    AppUser updateUser(UUID userId, AppUser updatedUser);

    boolean deleteUser(UUID userId);

    @HashPassword
    AppUser updateUserPassword(UUID userId, String newPassword);

    boolean verifyPassword(UUID userId, String rawPassword);

    AppUser lockUserAccount(UUID userId);

    AppUser unlockUserAccount(UUID userId);

    AppUser updateAccountStatus(UUID userId, AccountStatus accountStatus);

    Page<AppUser> findUsersByRole(Role role, Pageable pageable);

    Page<AppUser> findUsersByCity(String city, Pageable pageable);

    Page<AppUser> findUsersByCountry(String country, Pageable pageable);

    Page<AppUser> findUsersByAccountStatus(AccountStatus accountStatus, Pageable pageable);

    Page<AppUser> findUsersByLastLoginDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
