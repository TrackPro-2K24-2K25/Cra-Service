package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfileInfo {
    private String profilePictureUrl;
    private String bio;
    private String website;
    private String gender;
    private Date dateOfBirth;
}
