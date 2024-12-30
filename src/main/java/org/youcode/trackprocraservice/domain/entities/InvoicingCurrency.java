package org.youcode.trackprocraservice.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoicingCurrency")
public class InvoicingCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String value;


}
