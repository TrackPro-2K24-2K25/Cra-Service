package org.youcode.trackprocraservice.utils;

import org.youcode.trackprocraservice.domain.entities.Mission;
import org.youcode.trackprocraservice.exception.Mission.MissionException;

import java.util.Date;

public class MissionValidator {

    public static void validateMission(Mission mission) throws MissionException {
        if (mission == null) {
            throw new MissionException("Mission cannot be null.");
        }

        // Validate title
        if (mission.getTitle() == null || mission.getTitle().trim().isEmpty()) {
            throw new MissionException("Title is required.");
        }

        // Validate reference
        if (mission.getReference() == null || mission.getReference().trim().isEmpty()) {
            throw new MissionException("Reference is required.");
        }

        // Validate fees
        if (mission.getFees() == null || mission.getFees() <= 0) {
            throw new MissionException("Fees must be greater than 0.");
        }

        // Validate time unit
        if (mission.getTimeUnit() == null) {
            throw new MissionException("Time unit is required.");
        }

        // Validate mission duration
        if (mission.getMissionDuration() == null || mission.getMissionDuration() <= 0) {
            throw new MissionException("Mission duration must be greater than 0.");
        }

        // Validate start date
        if (mission.getStartDate() == null) {
            throw new MissionException("Start date is required.");
        }

        // Validate end date
        if (mission.getEndDate() == null) {
            throw new MissionException("End date is required.");
        }

        // Validate start date is before end date
        if (mission.getStartDate().after(mission.getEndDate())) {
            throw new MissionException("Start date must be before end date.");
        }

        // Validate company
        if (mission.getCompany() == null) {
            throw new MissionException("Company is required.");
        }

        // Validate payment term
        if (mission.getPaymentTerm() == null) {
            throw new MissionException("Payment term is required.");
        }

        // Validate supplier admin
        if (mission.getSupplierAdmin() == null) {
            throw new MissionException("Supplier admin is required.");
        }

        // Validate bank account
        if (mission.getBankAccount() == null) {
            throw new MissionException("Bank account is required.");
        }

        // Validate invoicing condition
        if (mission.getInvoicingCondition() == null) {
            throw new MissionException("Invoicing condition is required.");
        }

        // Validate service contract
        if (mission.getServiceContract() == null) {
            throw new MissionException("Service contract is required.");
        }
    }
}