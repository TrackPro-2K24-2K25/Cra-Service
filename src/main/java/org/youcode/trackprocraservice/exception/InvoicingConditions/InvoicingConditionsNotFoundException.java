package org.youcode.trackprocraservice.exception.InvoicingConditions;

import java.util.UUID;

public class InvoicingConditionsNotFoundException extends RuntimeException {

    public InvoicingConditionsNotFoundException(UUID id) {
        super("InvoicingCurrency not found with id: " + id);
    }

    public InvoicingConditionsNotFoundException(String message) {
        super(message);
    }
}