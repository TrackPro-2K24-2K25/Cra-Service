package org.youcode.trackprocraservice.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_term")
public class PaymentTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String value;
    private String description;
    private int days;
    private boolean isDefault;
    private boolean isActive;

}
