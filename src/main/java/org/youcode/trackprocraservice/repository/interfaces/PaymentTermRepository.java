package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, UUID> {
    Optional<PaymentTerm> findByValue(String value);
}
