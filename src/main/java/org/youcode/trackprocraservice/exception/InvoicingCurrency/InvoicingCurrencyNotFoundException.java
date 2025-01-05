package org.youcode.trackprocraservice.exception.InvoicingCurrency;

import java.util.UUID;

public class InvoicingCurrencyNotFoundException extends RuntimeException {

    public InvoicingCurrencyNotFoundException(UUID id) {
        super("InvoicingCurrency not found with id: " + id);
    }

    public InvoicingCurrencyNotFoundException(String message) {
        super(message);
    }
}
