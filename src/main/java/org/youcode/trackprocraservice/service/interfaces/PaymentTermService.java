package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;

import java.util.Optional;
import java.util.UUID;

public interface PaymentTermService {
    // Validate a PaymentTerm entity
    void validatePaymentTerm(PaymentTerm paymentTerm);

    // Create a new PaymentTerm
    PaymentTerm createPaymentTerm(PaymentTerm paymentTerm);

    // Retrieve paginated PaymentTerms
    Page<PaymentTerm> findAll(int page, int size);

    // Retrieve a PaymentTerm by ID
    Optional<PaymentTerm> getPaymentTermById(UUID id);

    // Update an existing PaymentTerm
    Optional<PaymentTerm> updatePaymentTerm(UUID id, PaymentTerm updatedPaymentTerm);

    boolean deletePaymentTerm(UUID id);
}
