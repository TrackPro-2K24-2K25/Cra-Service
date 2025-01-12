package org.youcode.trackprocraservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.exception.Company.CompanyException;
import org.youcode.trackprocraservice.repository.interfaces.CompanyRepository;
import org.youcode.trackprocraservice.service.interfaces.CompanyService;
import org.youcode.trackprocraservice.utils.CompanyValidator;

import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public Company createCompany(Company company) throws CompanyException {
        // Validate the company
        CompanyValidator.validateCompany(company);

        // Check if the company already exists
        if (companyRepository.existsByName(company.getName())) {
            throw new CompanyException("Company with the same name already exists");
        }

        // Save the company
        return companyRepository.save(company);
    }


    @Override
    public Company getCompanyById(UUID id) throws CompanyException {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException("Company not found with id: " + id));
    }


    @Override
    public Page<Company> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable);
    }


    @Override
    public Company updateCompany(UUID id, Company company) throws CompanyException {
        // Check if the company exists
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException("Company not found with id: " + id));

        // Validate the updated company
        CompanyValidator.validateCompany(company);

        // Update the company fields
        existingCompany.setName(company.getName());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setPays(company.getPays());
        existingCompany.setCompanyType(company.getCompanyType());
        existingCompany.setCreationDate(company.getCreationDate());
        existingCompany.setNRCS(company.getNRCS());
        existingCompany.setNIC(company.getNIC());
        existingCompany.setSIRET(company.getSIRET());
        existingCompany.setLegalForm(company.getLegalForm());
        existingCompany.setVat(company.getVat());
        existingCompany.setShareCapital(company.getShareCapital());
        existingCompany.setRCSCity(company.getRCSCity());
        existingCompany.setNote(company.getNote());

        // Save the updated company
        return companyRepository.save(existingCompany);
    }


    @Override
    public boolean deleteCompany(UUID id) {
        // Check if the company exists
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true; // Company was deleted
        }
        return false; // Company was not found
    }


    @Override
    public boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

}
