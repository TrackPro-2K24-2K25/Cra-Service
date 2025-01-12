package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.config.HashPassword;
import org.youcode.trackprocraservice.config.PasswordHashAspect;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;
import org.youcode.trackprocraservice.exception.User.UserNotFoundException;
import org.youcode.trackprocraservice.exception.User.UserValidationException;
import org.youcode.trackprocraservice.repository.impl.CustomAppUserRepositoryImpl;
import org.youcode.trackprocraservice.repository.interfaces.user.AppUserRepository;
import org.youcode.trackprocraservice.service.interfaces.UserService;
import org.youcode.trackprocraservice.utils.UserValidationUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;
    private final UserValidationUtils userValidationUtils;
    private final CustomAppUserRepositoryImpl customAppUserRepositoryImpl;

    @Autowired
    private PasswordHashAspect passwordHashAspect;

    @Autowired
    public UserServiceImpl(AppUserRepository appUserRepository, UserValidationUtils userValidationUtils, CustomAppUserRepositoryImpl customAppUserRepositoryImpl) {
        this.appUserRepository = appUserRepository;
        this.userValidationUtils = userValidationUtils;
        this.customAppUserRepositoryImpl = customAppUserRepositoryImpl;
    }

    // === CRUD Operations ===


    @HashPassword
    @Override
    public AppUser createUser(AppUser user) {
        // Validate user data
        if (!userValidationUtils.isValidUserData(user.getEmail(), user.getUsername(), user.getPassword(), user.getContactInfo().getPhoneNumber())) {
            throw new UserValidationException("Invalid user data.");
        }
        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new UserValidationException("Email is already in use.");
        }
        if (appUserRepository.existsByUsername(user.getUsername())) {
            throw new UserValidationException("Username is already in use.");
        }

        // Save the user
        return appUserRepository.save(user);
    }


    @Override
    public Optional<AppUser> getUserById(UUID userId) {
        return appUserRepository.findById(userId);
    }

    @Override
    public Page<AppUser> getAllUsers(Pageable pageable) {
        return appUserRepository.findAll(pageable);
    }

    @HashPassword
    @Override
    public AppUser updateUser(UUID userId, AppUser updatedUser) {
        AppUser existingUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));

        // Validate updated data
        if (!userValidationUtils.isValidUserData(updatedUser.getEmail(), updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getContactInfo().getPhoneNumber())) {
            throw new UserValidationException("Invalid user data.");
        }

        // Update fields
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setContactInfo(updatedUser.getContactInfo());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setProfileInfo(updatedUser.getProfileInfo());
        existingUser.setPreferences(updatedUser.getPreferences());
        existingUser.setSecurityInfo(updatedUser.getSecurityInfo());
        existingUser.setAuditInfo(updatedUser.getAuditInfo());

        return appUserRepository.save(existingUser);
    }

    @Override
    public boolean deleteUser(UUID userId) {
        if (appUserRepository.existsById(userId)) {
            appUserRepository.deleteById(userId);
            return true; // Deletion successful
        }
        return false; // User not found
    }



    // === Additional Methods ===

    @HashPassword
    @Override
    public AppUser updateUserPassword(UUID userId, String newPassword) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
        user.setPassword(newPassword); // Password is hashed by the @HashPassword aspect
        return appUserRepository.save(user);
    }

    @Override
    public boolean verifyPassword(UUID userId, String rawPassword) {
        // Find the user by ID or throw an exception if not found
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));

        // Use the PasswordHashAspect to verify the password
        return passwordHashAspect.verifyPassword(rawPassword, user.getPassword());
    }


    @Override
    public AppUser lockUserAccount(UUID userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
        user.getSecurityInfo().setLocked(true);
        return appUserRepository.save(user);
    }

    @Override
    public AppUser unlockUserAccount(UUID userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
        user.getSecurityInfo().setLocked(false);
        return appUserRepository.save(user);
    }


    @Override
    public AppUser updateAccountStatus(UUID userId, AccountStatus accountStatus) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
        user.getPreferences().setAccountStatus(accountStatus);
        return appUserRepository.save(user);
    }

    @Override
    public Page<AppUser> findUsersByRole(Role role, Pageable pageable) {
        return customAppUserRepositoryImpl.searchUsersByRole(role, pageable);
    }

    @Override
    public Page<AppUser> findUsersByCity(String city, Pageable pageable) {
        return customAppUserRepositoryImpl.searchUsersByCity(city, pageable); // Removed unnecessary cast
    }

    @Override
    public Page<AppUser> findUsersByCountry(String country, Pageable pageable) {
        return customAppUserRepositoryImpl.searchUsersByCountry(country, pageable);
    }

    @Override
    public Page<AppUser> findUsersByAccountStatus(AccountStatus accountStatus, Pageable pageable) {
        return customAppUserRepositoryImpl.searchUsersByAccountStatus(accountStatus, pageable);
    }


    @Override
    public Page<AppUser> findUsersByLastLoginDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return customAppUserRepositoryImpl.searchUsersByLastLoginDateRange(startDate, endDate, pageable);
    }












    }
