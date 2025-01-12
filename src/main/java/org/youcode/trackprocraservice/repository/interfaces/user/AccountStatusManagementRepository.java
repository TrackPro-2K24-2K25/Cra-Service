package org.youcode.trackprocraservice.repository.interfaces.user;

import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;

import java.util.UUID;

public interface AccountStatusManagementRepository {
    AppUser lockUserAccount(UUID userId);
    AppUser unlockUserAccount(UUID userId);
    AppUser updateAccountStatus(UUID userId, AccountStatus accountStatus);
}