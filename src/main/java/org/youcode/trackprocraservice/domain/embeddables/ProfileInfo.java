package org.youcode.trackprocraservice.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.youcode.trackprocraservice.config.Encrypted;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfileInfo {
    @Encrypted
    private String profilePictureUrl;
    private String bio;
    private String website;
    @Encrypted
    private String gender;
    @Encrypted
    private Date dateOfBirth;
}
