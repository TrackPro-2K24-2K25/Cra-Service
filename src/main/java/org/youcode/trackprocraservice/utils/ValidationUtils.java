package org.youcode.trackprocraservice.utils;

import org.youcode.trackprocraservice.domain.entities.PaymentTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.youcode.trackprocraservice.repository.interfaces.PaymentTermRepository;

import java.util.regex.Pattern;

@Component
public class ValidationUtils {

    // 3-letter uppercase currency codes (ISO 4217 standard)
    private static final Pattern VALID_CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");

    // Description length limits
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 255;

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    public boolean isValidCurrencyValue(String value) {
        return value != null && VALID_CURRENCY_PATTERN.matcher(value).matches();
    }

    public boolean isValidDescription(String description) {
        return description != null
                && description.length() >= DESCRIPTION_MIN_LENGTH
                && description.length() <= DESCRIPTION_MAX_LENGTH;
    }

    public boolean isValidDays(int days) {
        return days >= 0; // Days should be non-negative
    }

    public boolean isValueUnique(String value) {
        // Check if the value already exists in the database
        return paymentTermRepository.findByValue(value).isEmpty();
    }

    public boolean isValidPaymentTerm(String value, String description, int days) {
        return isValueUnique(value)
                && isValidDescription(description)
                && isValidDays(days);
    }
}