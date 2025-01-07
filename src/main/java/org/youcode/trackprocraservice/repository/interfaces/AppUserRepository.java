package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.trackprocraservice.domain.entities.AppUser;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email); // Find user by email
    Optional<AppUser> findByUsername(String username); // Find user by username
    boolean existsByEmail(String email); // Check if email exists
    boolean existsByUsername(String username); // Check if username exists
}

