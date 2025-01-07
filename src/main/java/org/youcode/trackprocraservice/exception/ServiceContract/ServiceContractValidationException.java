package org.youcode.trackprocraservice.exception.ServiceContract;

public class ServiceContractValidationException extends RuntimeException {
    public ServiceContractValidationException(String message) {
        super(message);
    }
}