package org.youcode.trackprocraservice.web.vm.Mission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissionResponseVM {

    private String title;
    private String reference;
    private UUID companyId;
    private UUID supplierAdminId;
    private UUID collaborateurId;
    private UUID paymentTermId;
    private UUID bankAccountId;
    private UUID invoicingConditionId;
    private UUID serviceContractId;
    private Boolean nonRenewable;
    private Boolean finalClient;
    private Boolean invoiceRecipient;
    private Date startDate;
    private Date endDate;
    private Double fees;

}
