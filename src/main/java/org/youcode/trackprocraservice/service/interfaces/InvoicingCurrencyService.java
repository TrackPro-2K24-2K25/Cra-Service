package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;

import java.util.UUID;

public interface InvoicingCurrencyService {

    InvoicingCurrency create(InvoicingCurrency invoicingCurrency);

    // to use pagination : done
    Page<InvoicingCurrency> findAll(int page, int size);

    InvoicingCurrency update(InvoicingCurrency updatedCurrency);

    boolean deleteById(UUID id);

    // Validation
    void validateCurrencyValue(String value);
}
