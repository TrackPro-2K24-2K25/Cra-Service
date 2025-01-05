package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;

import java.util.UUID;

public interface InvoicingCurrencyRepository  extends JpaRepository<InvoicingCurrency, UUID> {

}
