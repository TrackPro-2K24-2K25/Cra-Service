package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.InvoicingConditions;

import java.util.UUID;

public interface InvoicingConditionsService {
    InvoicingConditions create(InvoicingConditions invoicingConditions);

    Page<InvoicingConditions> findAll(int page, int size);

    InvoicingConditions findById(UUID id);

    InvoicingConditions update(UUID id, InvoicingConditions updatedInvoicingConditions);

    boolean delete(UUID id);
}
