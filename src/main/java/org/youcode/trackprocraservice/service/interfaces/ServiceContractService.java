package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;

import java.util.Optional;
import java.util.UUID;

public interface ServiceContractService {
    ServiceContract create(ServiceContract serviceContract);

    Page<ServiceContract> findAll(int page, int size);

    Optional<ServiceContract> findById(UUID id);

    ServiceContract update(UUID id, ServiceContract updatedServiceContract);

    boolean delete(UUID id);
}
