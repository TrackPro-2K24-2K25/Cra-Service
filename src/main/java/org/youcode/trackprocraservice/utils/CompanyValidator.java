package org.youcode.trackprocraservice.utils;


import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.exception.Company.CompanyException;

public class CompanyValidator {

    public static void validateCompany(Company company) throws CompanyException {
        if (company == null) {
            throw new CompanyException("Company cannot be null");
        }
        if (company.getName() == null || company.getName().trim().isEmpty()) {
            throw new CompanyException("Company name cannot be null or empty");
        }
        if (company.getAddress() == null || company.getAddress().trim().isEmpty()) {
            throw new CompanyException("Company address cannot be null or empty");
        }
        if (company.getPays() == null || company.getPays().trim().isEmpty()) {
            throw new CompanyException("Company country cannot be null or empty");
        }
        if (company.getCompanyType() == null) {
            throw new CompanyException("Company type cannot be null");
        }
        if (company.getCreationDate() == null) {
            throw new CompanyException("Company creation date cannot be null");
        }
        if (company.getNRCS() <= 0) {
            throw new CompanyException("Company Registration Number (NRCS) must be greater than 0");
        }
        if (company.getNIC() <= 0) {
            throw new CompanyException("National Identification Number (NIC) must be greater than 0");
        }
        if (company.getSIRET() <= 0) {
            throw new CompanyException("Company Establishment ID (SIRET) must be greater than 0");
        }
        if (company.getLegalForm() == null || company.getLegalForm().trim().isEmpty()) {
            throw new CompanyException("Legal form cannot be null or empty");
        }
        if (company.getVat() == null) {
            throw new CompanyException("VAT type cannot be null");
        }
        if (company.getShareCapital() < 0) {
            throw new CompanyException("Share capital cannot be negative");
        }
        if (company.getRCSCity() == null || company.getRCSCity().trim().isEmpty()) {
            throw new CompanyException("RCS City cannot be null or empty");
        }
    }
}
