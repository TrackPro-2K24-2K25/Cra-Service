package org.youcode.trackprocraservice.exception.PaymentTerm;

import java.util.UUID;

public class PaymentTermNotFoundException  extends RuntimeException {

    public PaymentTermNotFoundException(UUID id) {
        super("PaymentTerm not found with id: " + id);
    }

    public PaymentTermNotFoundException(String message) {
        super(message);
    }

}
