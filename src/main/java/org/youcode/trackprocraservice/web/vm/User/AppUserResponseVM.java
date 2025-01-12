package org.youcode.trackprocraservice.web.vm.User;

import lombok.Data;
import org.youcode.trackprocraservice.domain.embeddables.*;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;

@Data
public class AppUserResponseVM {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private ContactInfo contactInfo;
    private Address address;
    private ProfileInfo profileInfo;
    private Preferences preferences;
    private AuditInfo auditInfo;
    private Role role;
    private AccountStatus accountStatus;
}