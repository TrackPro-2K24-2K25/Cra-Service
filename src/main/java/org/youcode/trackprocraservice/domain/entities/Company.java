package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import org.youcode.trackprocraservice.domain.enums.CompanyType;
import org.youcode.trackprocraservice.domain.enums.VAT;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // General company information
    private String name;
    private String address;  // Translated from "adresse"
    private String pays;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private int NRCS;  // Company Registration Number
    private int NIC;   // National Identification Number
    private int SIRET; // Company Establishment ID (in France)

    private String legalForm;  // Translated from "formeJuridique"

    @Enumerated(EnumType.STRING)
    private VAT vat;  // VAT type (using the VAT enum)

    private int shareCapital;  // Translated from "capitaleSocial"
    private String RCSCity;  // Translated from "villeRCS" (RCS: Register of Companies)
    private String note;

    @OneToMany(mappedBy = "company") // MappedBy refers to the field in Mission referencing Company
    private List<Mission> missions;

    // relation
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts; // Corrected mappedBy

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_conditions_id")
    private InvoicingConditions invoicingConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delai_reglement_id")
    private PaymentTerm paymentTerm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_currency_id")
    private InvoicingCurrency invoicingCurrency;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = true)
    private AppUser manager;

}
