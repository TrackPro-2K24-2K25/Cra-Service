package org.youcode.trackprocraservice.repository.interfaces;

import org.youcode.trackprocraservice.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    // Custom query methods can be added here if needed
    Company findByName(String name);
    boolean existsByName(String name);
}