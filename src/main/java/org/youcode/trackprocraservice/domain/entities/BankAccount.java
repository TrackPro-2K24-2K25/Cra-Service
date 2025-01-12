package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="bank_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String accountNumber;
    private String bankName;
    private String iban;
    private String bic;    //swift code

    // relation
    @ManyToOne(fetch = FetchType.LAZY)  // Many-to-One relationship
    @JoinColumn(name = "company_id")  // Foreign key to the Company entity
    private Company company;

    @OneToMany(mappedBy = "bankAccount") // MappedBy refers to the field in Mission referencing BankAccount
    private List<Mission> missions;

}
