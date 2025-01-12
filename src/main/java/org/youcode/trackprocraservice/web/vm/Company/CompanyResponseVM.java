package org.youcode.trackprocraservice.web.vm.Company;

import lombok.*;
import org.youcode.trackprocraservice.domain.enums.CompanyType;
import org.youcode.trackprocraservice.domain.enums.VAT;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseVM {

    private String name;
    private String address;
    private String pays;
    private CompanyType companyType;
    private LocalDateTime creationDate;
    private int NRCS;
    private int NIC;
    private int SIRET;
    private String legalForm;
    private VAT vat;
    private int shareCapital;
    private String RCSCity;
    private String note;

}
