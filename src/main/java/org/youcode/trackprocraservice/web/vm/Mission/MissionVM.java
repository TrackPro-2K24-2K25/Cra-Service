package org.youcode.trackprocraservice.web.vm.Mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionVM {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Reference is required")
    @Size(max = 50, message = "Reference must be less than 50 characters")
    private String reference;

    @NotNull(message = "Company ID is required")
    private UUID companyId;

    @NotNull(message = "Supplier Admin ID is required")
    private UUID supplierAdminId;

    @NotNull(message = "Collaborateur ID is required")
    private UUID collaborateurId;

    @NotNull(message = "Payment Term ID is required")
    private UUID paymentTermId;

    @NotNull(message = "Bank Account ID is required")
    private UUID bankAccountId;

    @NotNull(message = "Invoicing Condition ID is required")
    private UUID invoicingConditionId;

    @NotNull(message = "Service Contract ID is required")
    private UUID serviceContractId;

    @NotNull(message = "Non-renewable flag is required")
    private Boolean nonRenewable;

    @NotNull(message = "Final Client flag is required")
    private Boolean finalClient;

    @NotNull(message = "Invoice Recipient flag is required")
    private Boolean invoiceRecipient;

    @NotNull(message = "Start Date is required")
    private Date startDate;

    @NotNull(message = "End Date is required")
    private Date endDate;

    @PositiveOrZero(message = "Fees must be a positive number or zero")
    private Double fees;
}
