package org.youcode.trackprocraservice.domain.embeddables;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.youcode.trackprocraservice.config.Encrypted;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Encrypted
    private String streetAddress;
    private String city;
    private String state;
    @Encrypted
    private String postalCode;
    private String country;
}
