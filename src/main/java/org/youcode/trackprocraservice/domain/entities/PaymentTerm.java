package org.youcode.trackprocraservice.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String value;
    private String description;
    private int days;
    private boolean isDefault;
    private boolean isActive;

    @OneToMany(mappedBy = "paymentTerm", fetch = FetchType.LAZY)
    private List<InvoicingConditions> invoicingConditions;

}
