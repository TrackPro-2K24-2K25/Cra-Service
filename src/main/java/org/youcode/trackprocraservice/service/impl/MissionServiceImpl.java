package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.Mission;
import org.youcode.trackprocraservice.exception.Mission.MissionException;
import org.youcode.trackprocraservice.repository.interfaces.MissionRepository;
import org.youcode.trackprocraservice.utils.MissionValidator;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class MissionServiceImpl implements org.youcode.trackprocraservice.service.interfaces.MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // ==================== CRUD Operations ====================


    @Override
    public Mission createMission(Mission mission) throws MissionException {
        // Validate the mission
        MissionValidator.validateMission(mission);

        // Save the mission
        return missionRepository.save(mission);
    }


    @Override
    public Optional<Mission> getMissionById(UUID id) {
        return missionRepository.findById(id);
    }


    @Override
    public Page<Mission> getAllMissions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAll(pageable);
    }

    @Override
    public Mission updateMission(UUID id, Mission updatedMission) throws MissionException {
        // Validate the updated mission
        MissionValidator.validateMission(updatedMission);

        // Update the mission
        return missionRepository.findById(id).map(existingMission -> {
            existingMission.setTitle(updatedMission.getTitle());
            existingMission.setReference(updatedMission.getReference());
            existingMission.setFees(updatedMission.getFees());
            existingMission.setTimeUnit(updatedMission.getTimeUnit());
            existingMission.setMissionDuration(updatedMission.getMissionDuration());
            existingMission.setStartDate(updatedMission.getStartDate());
            existingMission.setEndDate(updatedMission.getEndDate());
            existingMission.setNonRenewable(updatedMission.getNonRenewable());
            existingMission.setFinalClient(updatedMission.getFinalClient());
            existingMission.setInvoiceRecipient(updatedMission.getInvoiceRecipient());
            existingMission.setCompany(updatedMission.getCompany());
            existingMission.setPaymentTerm(updatedMission.getPaymentTerm());
            existingMission.setSupplierAdmin(updatedMission.getSupplierAdmin());
            existingMission.setCollaborateur(updatedMission.getCollaborateur());
            existingMission.setBankAccount(updatedMission.getBankAccount());
            existingMission.setInvoicingCondition(updatedMission.getInvoicingCondition());
            existingMission.setServiceContract(updatedMission.getServiceContract());

            return missionRepository.save(existingMission);
        }).orElseThrow(() -> new MissionException("Mission not found with id: " + id));
    }

    @Override
    public boolean deleteMission(UUID id) {
        if (missionRepository.existsById(id)) {
            missionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ==================== Other Repository Methods ====================

    @Override
    public Page<Mission> getMissionsByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByTitleContainingIgnoreCase(title, pageable);
    }


    @Override
    public Page<Mission> getMissionsByReference(String reference, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByReference(reference, pageable);
    }

    @Override
    public Page<Mission> getMissionsByCompanyId(UUID companyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public Page<Mission> getMissionsBySupplierAdminId(UUID supplierAdminId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findBySupplierAdminId(supplierAdminId, pageable);
    }

    @Override
    public Page<Mission> getMissionsByCollaborateurId(UUID collaborateurId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByCollaborateurId(collaborateurId, pageable);
    }

    @Override
    public Page<Mission> getMissionsByPaymentTermId(UUID paymentTermId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByPaymentTermId(paymentTermId, pageable);
    }

    @Override
    public Page<Mission> getMissionsByBankAccountId(UUID bankAccountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByBankAccountId(bankAccountId, pageable);
    }


    @Override
    public Page<Mission> getMissionsByInvoicingConditionId(UUID invoicingConditionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByInvoicingConditionId(invoicingConditionId, pageable);
    }

    @Override
    public Page<Mission> getMissionsByServiceContractId(UUID serviceContractId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByServiceContractId(serviceContractId, pageable);
    }

    @Override
    public Page<Mission> getMissionsByNonRenewable(boolean nonRenewable, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByNonRenewable(nonRenewable, pageable);
    }

    @Override
    public Page<Mission> getMissionsByFinalClient(boolean finalClient, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByFinalClient(finalClient, pageable);
    }

    @Override
    public Page<Mission> getMissionsByInvoiceRecipient(boolean invoiceRecipient, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByInvoiceRecipient(invoiceRecipient, pageable);
    }

    @Override
    public Page<Mission> getMissionsByDateRange(Date startDate, Date endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByStartDateBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Mission> getMissionsByFeesGreaterThan(Double fees, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByFeesGreaterThan(fees, pageable);
    }

    @Override
    public Page<Mission> getMissionsByFeesLessThan(Double fees, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByFeesLessThan(fees, pageable);
    }

}
