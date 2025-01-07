package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoicing_conditions")
@Getter
@Setter
public class InvoicingConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "payment_term_id", nullable = false)
    private PaymentTerm paymentTerm;  // Relationship with PaymentTerm

    private double discount;          // Discount percentage for early payment (e.g., 2%)
    private int daysForDiscount;      // Number of days to receive the discount (e.g., 10 days)
    private double lateFeeRate;       // Late fee rate (e.g., 1.5% per month)
    private boolean isActive;

    @OneToMany(mappedBy = "invoicingConditions", fetch = FetchType.LAZY)
    private List<Company> companies;

    @OneToMany(mappedBy = "invoicingCondition")
    private List<Mission> missions;
}









