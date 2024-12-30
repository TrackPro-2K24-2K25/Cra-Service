package org.youcode.trackprocraservice.domain.entities;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.youcode.trackprocraservice.domain.enums.TimeUnit;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "title", nullable = false)
    private String title;



    @Column(name = "reference", nullable = false) // Assuming "porte" means "reference"
    private String reference;


    @Column(name = "fees", nullable = false)
    private Double fees;


    @Enumerated(EnumType.STRING)
    @Column(name = "timeUnit", nullable = false)
    private TimeUnit timeUnit;

    @Column(name = "mission_duration", nullable = false)
    private Integer missionDuration;

    @NotNull
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


    @OneToMany(mappedBy = "paymentTerm") // MappedBy refers to the field in Mission referencing PaymentTerm
    private List<Mission> missions;


//    @Column(name = "supplierAdminId")
//    private UUID supplierAdminId;
//
//    @Column(name = "collaboratorId")
//    private UUID collaboratorId;

    @ManyToOne
    @JoinColumn(name = "bankAccountId")
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "paymentTermId")
    private PaymentTerm paymentTerm;

    @ManyToOne
    @JoinColumn(name = "invoicingConditionId")
    private InvoicingConditions invoicingCondition;

   @ManyToOne
   @JoinColumn(name = "serviceContractId")
   private ServiceContract serviceContract;



}
