package org.youcode.trackprocraservice.repository.interfaces.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {



    // Find a user by email (encrypted field)
    @Query("SELECT u FROM AppUser u WHERE u.email = :email")
    Optional<AppUser> findByEmail(@Param("email") String email);

    // Find a user by username
    Optional<AppUser> findByUsername(String username);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Check if a user exists by username
    boolean existsByUsername(String username);

    // Find users by role
    @Query("SELECT u FROM AppUser u WHERE u.role = :role")
    List<AppUser> findByRole(@Param("role") Role role);

    // Find users by account status
    @Query("SELECT u FROM AppUser u WHERE u.preferences.accountStatus = :accountStatus")
    List<AppUser> findByAccountStatus(@Param("accountStatus") AccountStatus accountStatus);

    // Find users by city (from Address embeddable)
    @Query("SELECT u FROM AppUser u WHERE u.address.city = :city")
    List<AppUser> findByCity(@Param("city") String city);

    // Find users by country (from Address embeddable)
    @Query("SELECT u FROM AppUser u WHERE u.address.country = :country")
    List<AppUser> findByCountry(@Param("country") String country);

    // Find users by last login date (from AuditInfo embeddable)
    @Query("SELECT u FROM AppUser u WHERE u.auditInfo.lastLoginAt = :lastLoginAt")
    List<AppUser> findByLastLoginAt(@Param("lastLoginAt") String lastLoginAt);
}

