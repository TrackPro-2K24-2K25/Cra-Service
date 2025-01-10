package org.youcode.trackprocraservice.repository.interfaces.user;

import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface UserSearchRepository {
    Page<AppUser> searchUsersByRole(Role role, Pageable pageable);
    Page<AppUser> searchUsersByCity(String city, Pageable pageable);
    Page<AppUser> searchUsersByCountry(String country, Pageable pageable);
    Page<AppUser> searchUsersByAccountStatus(AccountStatus accountStatus, Pageable pageable);
    Page<AppUser> searchUsersByLastLoginDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}