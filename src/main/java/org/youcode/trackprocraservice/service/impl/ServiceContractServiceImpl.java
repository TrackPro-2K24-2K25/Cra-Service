package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;
import org.youcode.trackprocraservice.exception.ServiceContract.ServiceContractNotFoundException;
import org.youcode.trackprocraservice.exception.ServiceContract.ServiceContractValidationException;
import org.youcode.trackprocraservice.repository.interfaces.ServiceContractRepository;
import org.youcode.trackprocraservice.service.interfaces.ServiceContractService;
import org.youcode.trackprocraservice.utils.ValidationUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceContractServiceImpl implements ServiceContractService {
    private final ServiceContractRepository repository;
    private final ValidationUtils validationUtils;

    @Autowired
    public ServiceContractServiceImpl(ServiceContractRepository repository, ValidationUtils validationUtils) {
        this.repository = repository;
        this.validationUtils = validationUtils;
    }

    @Override
    public ServiceContract create(ServiceContract serviceContract) {
        if (!validationUtils.isValidServiceContract(serviceContract.getName(), serviceContract.getDescription())) {
            throw new ServiceContractValidationException("Invalid ServiceContract data");
        }
        return repository.save(serviceContract);
    }

    @Override
    public Page<ServiceContract> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    @Override
    public Optional<ServiceContract> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public ServiceContract update(UUID id, ServiceContract updatedServiceContract) {
        return repository.findById(id).map(existingServiceContract -> {
            if (!validationUtils.isValidServiceContract(updatedServiceContract.getName(), updatedServiceContract.getDescription())) {
                throw new ServiceContractValidationException("Invalid ServiceContract data");
            }

            existingServiceContract.setName(updatedServiceContract.getName());
            existingServiceContract.setDescription(updatedServiceContract.getDescription());

            return repository.save(existingServiceContract);
        }).orElseThrow(() -> new ServiceContractNotFoundException(id));
    }


    @Override
    public boolean delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ServiceContractNotFoundException(id);
        }
        repository.deleteById(id);
        return true;
    }



}
