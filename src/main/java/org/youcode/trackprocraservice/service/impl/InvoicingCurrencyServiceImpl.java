package org.youcode.trackprocraservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyNotFoundException;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyValidationException;
import org.youcode.trackprocraservice.repository.interfaces.InvoicingCurrencyRepository;
import org.youcode.trackprocraservice.service.interfaces.InvoicingCurrencyService;
import org.youcode.trackprocraservice.utils.ValidationUtils;

import java.util.UUID;

@Service
@Transactional
public class InvoicingCurrencyServiceImpl implements InvoicingCurrencyService {
    private final InvoicingCurrencyRepository repository;

    public InvoicingCurrencyServiceImpl(InvoicingCurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvoicingCurrency create(InvoicingCurrency invoicingCurrency) {
        validateCurrencyValue(invoicingCurrency.getValue());
        return repository.save(invoicingCurrency);
    }

    // to use pagination : done
    @Override
    public Page<InvoicingCurrency> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    @Override
    public InvoicingCurrency update(InvoicingCurrency updatedCurrency) {
        validateCurrencyValue(updatedCurrency.getValue());
        InvoicingCurrency existingCurrency = repository.findById(updatedCurrency.getId())
                .orElseThrow(() -> new InvoicingCurrencyNotFoundException(updatedCurrency.getId()));

        existingCurrency.setValue(updatedCurrency.getValue());
        return repository.save(existingCurrency);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new InvoicingCurrencyNotFoundException(id);
        }
        repository.deleteById(id);
        return true;
    }



    // Validation
    @Override
    public void validateCurrencyValue(String value) {
        if (!ValidationUtils.isValidCurrencyValue(value)) {
            throw new InvoicingCurrencyValidationException("Invalid currency value: " + value);
        }
    }
}
