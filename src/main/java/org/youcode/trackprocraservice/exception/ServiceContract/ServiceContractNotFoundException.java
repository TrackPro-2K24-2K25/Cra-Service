package org.youcode.trackprocraservice.exception.ServiceContract;

import java.util.UUID;

public class ServiceContractNotFoundException extends RuntimeException {
    public ServiceContractNotFoundException(UUID id) {
        super("ServiceContract not found with ID: " + id);
    }
}