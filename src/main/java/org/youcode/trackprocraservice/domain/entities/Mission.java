package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.trackprocraservice.domain.enums.TimeUnit;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "fees", nullable = false)
    private Double fees;

    @Enumerated(EnumType.STRING)
    @Column(name = "timeUnit", nullable = false)
    private TimeUnit timeUnit;

    @Column(name = "mission_duration", nullable = false)
    private Integer missionDuration;

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "nonRenewable", nullable = false)
    private Boolean nonRenewable = false;

    @Column(name = "finalClient")
    private Boolean finalClient;

    @Column(name = "invoiceRecipient")
    private Boolean invoiceRecipient;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "paymentTermId")
    private PaymentTerm paymentTerm;

    @ManyToOne
    @JoinColumn(name = "supplier_admin_id")
    private AppUser supplierAdmin;

    @ManyToOne
    @JoinColumn(name = "collaborateur_id", nullable = true) // Nullable as needed
    private AppUser collaborateur;

    @ManyToOne
    @JoinColumn(name = "bankAccountId")
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "invoicingConditionId")
    private InvoicingConditions invoicingCondition;

    @ManyToOne
    @JoinColumn(name = "serviceContractId")
    private ServiceContract serviceContract;
}
