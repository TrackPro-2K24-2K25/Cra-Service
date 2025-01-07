package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.trackprocraservice.domain.entities.InvoicingConditions;

import java.util.UUID;

public interface InvoicingConditionsRepository extends JpaRepository<InvoicingConditions, UUID> {
}
