package org.youcode.trackprocraservice.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    // 3-letter uppercase currency codes (ISO 4217 standard)
    private static final Pattern VALID_CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");

    public static boolean isValidCurrencyValue(String value) {
        return value != null && VALID_CURRENCY_PATTERN.matcher(value).matches();
    }


}
