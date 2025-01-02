package org.youcode.trackprocraservice.domain.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Preferences {
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private boolean isTwoFactorEnabled;
    private boolean isTermsAccepted;
    private String preferredLanguage;
}
