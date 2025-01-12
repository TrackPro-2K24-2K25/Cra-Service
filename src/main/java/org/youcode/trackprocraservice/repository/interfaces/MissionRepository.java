package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.trackprocraservice.domain.entities.Mission;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {

    Page<Mission> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Mission> findByReference(String reference, Pageable pageable);
    Page<Mission> findByCompanyId(UUID companyId, Pageable pageable);
    Page<Mission> findBySupplierAdminId(UUID supplierAdminId, Pageable pageable);
    Page<Mission> findByCollaborateurId(UUID collaborateurId, Pageable pageable);
    Page<Mission> findByPaymentTermId(UUID paymentTermId, Pageable pageable);
    Page<Mission> findByBankAccountId(UUID bankAccountId, Pageable pageable);
    Page<Mission> findByInvoicingConditionId(UUID invoicingConditionId, Pageable pageable);
    Page<Mission> findByServiceContractId(UUID serviceContractId, Pageable pageable);
    Page<Mission> findByNonRenewable(boolean nonRenewable, Pageable pageable);
    Page<Mission> findByFinalClient(boolean finalClient, Pageable pageable);
    Page<Mission> findByInvoiceRecipient(boolean invoiceRecipient, Pageable pageable);
    Page<Mission> findByStartDateBetween(Date startDate, Date endDate, Pageable pageable);
    Page<Mission> findByFeesGreaterThan(Double fees, Pageable pageable);
    Page<Mission> findByFeesLessThan(Double fees, Pageable pageable);

}