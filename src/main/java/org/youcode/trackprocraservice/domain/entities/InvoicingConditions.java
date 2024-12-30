package org.youcode.trackprocraservice.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoicing_conditions")
@Getter
@Setter
public class InvoicingConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentTerms;  // e.g., "30 days net"
    private double discount;      // Discount percentage for early payment (e.g., 2%)
    private int daysForDiscount;  // Number of days to receive the discount (e.g., 10 days)
    private double lateFeeRate;   // Late fee rate (e.g., 1.5% per month)
    private boolean isActive;

    @OneToMany(mappedBy = "invoicingConditions", fetch = FetchType.LAZY)
    private List<Company> companies;

    @OneToMany(mappedBy = "invoicingCondition") // MappedBy refers to the field in Mission referencing InvoicingConditions
    private List<Mission> missions;




}
