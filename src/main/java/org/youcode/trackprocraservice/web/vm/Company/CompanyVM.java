package org.youcode.trackprocraservice.web.vm.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.*;
import org.youcode.trackprocraservice.domain.enums.CompanyType;
import org.youcode.trackprocraservice.domain.enums.VAT;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVM {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must be less than 50 characters")
    private String pays;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDateTime creationDate;

    @NotNull(message = "NRCS is required")
    @Min(value = 1, message = "NRCS must be greater than 0")
    private int NRCS;

    @NotNull(message = "NIC is required")
    @Min(value = 1, message = "NIC must be greater than 0")
    private int NIC;

    @NotNull(message = "SIRET is required")
    @Min(value = 1, message = "SIRET must be greater than 0")
    private int SIRET;

    @NotBlank(message = "Legal form is required")
    @Size(max = 50, message = "Legal form must be less than 50 characters")
    private String legalForm;

    @NotNull(message = "VAT type is required")
    private VAT vat;

    @NotNull(message = "Share capital is required")
    @Min(value = 0, message = "Share capital cannot be negative")
    private int shareCapital;

    @NotBlank(message = "RCS City is required")
    @Size(max = 50, message = "RCS City must be less than 50 characters")
    private String RCSCity;

    @Size(max = 500, message = "Note must be less than 500 characters")
    private String note;
}