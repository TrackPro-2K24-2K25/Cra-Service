package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;
import org.youcode.trackprocraservice.exception.PaymentTerm.PaymentTermValidationException;
import org.youcode.trackprocraservice.repository.interfaces.PaymentTermRepository;
import org.youcode.trackprocraservice.service.interfaces.PaymentTermService;
import org.youcode.trackprocraservice.utils.ValidationUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentTermServiceImpl implements PaymentTermService {

    private final PaymentTermRepository paymentTermRepository;
    private final ValidationUtils validationUtils;

    @Autowired
    public PaymentTermServiceImpl(PaymentTermRepository paymentTermRepository, ValidationUtils validationUtils) {
        this.paymentTermRepository = paymentTermRepository;
        this.validationUtils = validationUtils;
    }

    // Validate a PaymentTerm entity
    public void validatePaymentTerm(PaymentTerm paymentTerm) {
        if (paymentTerm == null) {
            throw new PaymentTermValidationException("PaymentTerm cannot be null.");
        }
        if (!validationUtils.isValidDescription(paymentTerm.getDescription())) {
            throw new PaymentTermValidationException("Invalid description. Description must be between 5 and 255 characters.");
        }
        if (!validationUtils.isValidDays(paymentTerm.getDays())) {
            throw new PaymentTermValidationException("Invalid days. Days must be a non-negative number.");
        }
        if (!validationUtils.isValueUnique(paymentTerm.getValue())) {
            throw new PaymentTermValidationException("Value must be unique.");
        }
    }

    // Create a new PaymentTerm
    @Override
    public PaymentTerm createPaymentTerm(PaymentTerm paymentTerm) {
        validatePaymentTerm(paymentTerm);
        return paymentTermRepository.save(paymentTerm);
    }

    // Retrieve paginated PaymentTerms
    @Override
    public Page<PaymentTerm> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentTermRepository.findAll(pageable);
    }

    // Retrieve a PaymentTerm by ID
    @Override
    public Optional<PaymentTerm> getPaymentTermById(UUID id) {
        return paymentTermRepository.findById(id);
    }

    // Update an existing PaymentTerm
    @Override
    public Optional<PaymentTerm> updatePaymentTerm(UUID id, PaymentTerm updatedPaymentTerm) {
        return paymentTermRepository.findById(id).map(existingPaymentTerm -> {
            validatePaymentTerm(updatedPaymentTerm);

            existingPaymentTerm.setValue(updatedPaymentTerm.getValue());
            existingPaymentTerm.setDescription(updatedPaymentTerm.getDescription());
            existingPaymentTerm.setDays(updatedPaymentTerm.getDays());
            existingPaymentTerm.setDefault(updatedPaymentTerm.isDefault());
            existingPaymentTerm.setActive(updatedPaymentTerm.isActive());

            return paymentTermRepository.save(existingPaymentTerm);
        });
    }

    // Delete a PaymentTerm by ID
    @Override
    public boolean deletePaymentTerm(UUID id) {
        Optional<PaymentTerm> paymentTerm = paymentTermRepository.findById(id);
        if (paymentTerm.isPresent()) {
            paymentTermRepository.delete(paymentTerm.get());
            return true;
        }
        return false;
    }
}