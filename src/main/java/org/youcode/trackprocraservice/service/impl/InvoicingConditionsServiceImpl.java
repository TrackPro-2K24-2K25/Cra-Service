package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.InvoicingConditions;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyNotFoundException;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyValidationException;
import org.youcode.trackprocraservice.repository.interfaces.InvoicingConditionsRepository;
import org.youcode.trackprocraservice.repository.interfaces.PaymentTermRepository;
import org.youcode.trackprocraservice.service.interfaces.InvoicingConditionsService;
import org.youcode.trackprocraservice.utils.ValidationUtils;

import java.util.UUID;

@Service
public class InvoicingConditionsServiceImpl implements InvoicingConditionsService {
    private final InvoicingConditionsRepository repository;
    private final PaymentTermRepository paymentTermRepository;
    private final ValidationUtils validationUtils;

    @Autowired
    public InvoicingConditionsServiceImpl(InvoicingConditionsRepository repository, PaymentTermRepository paymentTermRepository, ValidationUtils validationUtils) {
        this.repository = repository;
        this.paymentTermRepository = paymentTermRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public InvoicingConditions create(InvoicingConditions invoicingConditions) {
        // Validate the input
        if (!validationUtils.isValidInvoicingConditions(
                invoicingConditions.getDiscount(),
                invoicingConditions.getDaysForDiscount(),
                invoicingConditions.getLateFeeRate(),
                invoicingConditions.getPaymentTerm().getId())) {
            throw new InvoicingCurrencyValidationException("Invalid invoicing conditions data");
        }

        // Fetch the PaymentTerm
        PaymentTerm paymentTerm = paymentTermRepository.findById(invoicingConditions.getPaymentTerm().getId())
                .orElseThrow(() -> new InvoicingCurrencyValidationException("PaymentTerm not found with ID: " + invoicingConditions.getPaymentTerm().getId()));

        // Set the PaymentTerm in the InvoicingConditions entity
        invoicingConditions.setPaymentTerm(paymentTerm);

        // Save the entity
        return repository.save(invoicingConditions);
    }


    @Override
    public Page<InvoicingConditions> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    @Override
    public InvoicingConditions findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new InvoicingCurrencyNotFoundException(id));
    }


    @Override
    public InvoicingConditions update(UUID id, InvoicingConditions updatedInvoicingConditions) {
        // Validate the input
        if (!validationUtils.isValidInvoicingConditions(
                updatedInvoicingConditions.getDiscount(),
                updatedInvoicingConditions.getDaysForDiscount(),
                updatedInvoicingConditions.getLateFeeRate(),
                updatedInvoicingConditions.getPaymentTerm().getId())) {
            throw new InvoicingCurrencyValidationException("Invalid invoicing conditions data");
        }

        // Fetch the existing entity
        InvoicingConditions existingEntity = repository.findById(id)
                .orElseThrow(() -> new InvoicingCurrencyNotFoundException(id));

        // Fetch the PaymentTerm
        PaymentTerm paymentTerm = paymentTermRepository.findById(updatedInvoicingConditions.getPaymentTerm().getId())
                .orElseThrow(() -> new InvoicingCurrencyValidationException("PaymentTerm not found with ID: " + updatedInvoicingConditions.getPaymentTerm().getId()));

        // Update the fields
        existingEntity.setPaymentTerm(paymentTerm);
        existingEntity.setDiscount(updatedInvoicingConditions.getDiscount());
        existingEntity.setDaysForDiscount(updatedInvoicingConditions.getDaysForDiscount());
        existingEntity.setLateFeeRate(updatedInvoicingConditions.getLateFeeRate());
        existingEntity.setActive(updatedInvoicingConditions.isActive());

        // Save the updated entity
        return repository.save(existingEntity);
    }

    @Override
    public boolean delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new InvoicingCurrencyNotFoundException(id);
        }
        repository.deleteById(id);
        return true;
    }

}
