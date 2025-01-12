package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.Mission;
import org.youcode.trackprocraservice.exception.Mission.MissionException;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface MissionService {
    Mission createMission(Mission mission) throws MissionException;

    Optional<Mission> getMissionById(UUID id);

    Page<Mission> getAllMissions(int page, int size);

    Mission updateMission(UUID id, Mission updatedMission) throws MissionException;

    boolean deleteMission(UUID id);

    Page<Mission> getMissionsByTitle(String title, int page, int size);

    Page<Mission> getMissionsByReference(String reference, int page, int size);

    Page<Mission> getMissionsByCompanyId(UUID companyId, int page, int size);

    Page<Mission> getMissionsBySupplierAdminId(UUID supplierAdminId, int page, int size);

    Page<Mission> getMissionsByCollaborateurId(UUID collaborateurId, int page, int size);

    Page<Mission> getMissionsByPaymentTermId(UUID paymentTermId, int page, int size);

    Page<Mission> getMissionsByBankAccountId(UUID bankAccountId, int page, int size);

    Page<Mission> getMissionsByInvoicingConditionId(UUID invoicingConditionId, int page, int size);

    Page<Mission> getMissionsByServiceContractId(UUID serviceContractId, int page, int size);

    Page<Mission> getMissionsByNonRenewable(boolean nonRenewable, int page, int size);

    Page<Mission> getMissionsByFinalClient(boolean finalClient, int page, int size);

    Page<Mission> getMissionsByInvoiceRecipient(boolean invoiceRecipient, int page, int size);

    Page<Mission> getMissionsByDateRange(Date startDate, Date endDate, int page, int size);

    Page<Mission> getMissionsByFeesGreaterThan(Double fees, int page, int size);

    Page<Mission> getMissionsByFeesLessThan(Double fees, int page, int size);
}
