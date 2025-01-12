package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.exception.Company.CompanyException;

import java.util.UUID;

public interface CompanyService {
    Company createCompany(Company company) throws CompanyException;

    Company getCompanyById(UUID id) throws CompanyException;

    Page<Company> findAll(int page, int size);

    Company updateCompany(UUID id, Company company) throws CompanyException;

    boolean deleteCompany(UUID id);

    boolean existsByName(String name);
}
