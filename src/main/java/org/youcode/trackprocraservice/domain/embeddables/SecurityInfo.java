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
    private String securityAnswer;
    private boolean isLocked;
    private int failedLoginAttempts;

    // Custom getter for isLocked to follow the JavaBean convention
    public boolean isLocked() {
        return isLocked;
    }
}


