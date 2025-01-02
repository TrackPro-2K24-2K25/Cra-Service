package org.youcode.trackprocraservice.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityInfo {
    private String securityQuestion;
    private String securityAnswer;  // Make sure to hash this if sensitive
    private boolean isLocked;
    private int failedLoginAttempts;
}
