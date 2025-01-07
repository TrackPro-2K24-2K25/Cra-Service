package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;

import java.util.Optional;
import java.util.UUID;

public interface ServiceContractRepository extends JpaRepository<ServiceContract, UUID> {
    Optional<ServiceContract> findByName(String name); // For checking unique name
}
