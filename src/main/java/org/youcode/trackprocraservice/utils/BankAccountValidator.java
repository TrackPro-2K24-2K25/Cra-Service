package org.youcode.trackprocraservice.utils;

import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.exception.BankAccount.BankAccountException;

public class BankAccountValidator {

    /**
     * Validates a BankAccount entity.
     *
     * @param bankAccount The BankAccount entity to validate.
     * @throws BankAccountException If the BankAccount is invalid.
     */
    public static void validateBankAccount(BankAccount bankAccount) throws BankAccountException {
        if (bankAccount == null) {
            throw new BankAccountException("Bank account cannot be null");
        }

        // Validate account number
        validateAccountNumber(bankAccount.getAccountNumber());

        // Validate bank name
        validateBankName(bankAccount.getBankName());

        // Validate IBAN
        validateIban(bankAccount.getIban());

        // Validate BIC (SWIFT code)
        validateBic(bankAccount.getBic());
    }

    /**
     * Validates the account number.
     *
     * @param accountNumber The account number to validate.
     * @throws BankAccountException If the account number is invalid.
     */
    private static void validateAccountNumber(String accountNumber) throws BankAccountException {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new BankAccountException("Account number is required");
        }
        if (accountNumber.length() < 10 || accountNumber.length() > 20) {
            throw new BankAccountException("Account number must be between 10 and 20 characters");
        }
        if (!accountNumber.matches("^[0-9]+$")) {
            throw new BankAccountException("Account number must contain only digits");
        }
    }

    /**
     * Validates the bank name.
     *
     * @param bankName The bank name to validate.
     * @throws BankAccountException If the bank name is invalid.
     */
    private static void validateBankName(String bankName) throws BankAccountException {
        if (bankName == null || bankName.trim().isEmpty()) {
            throw new BankAccountException("Bank name is required");
        }
        if (bankName.length() > 100) {
            throw new BankAccountException("Bank name must be less than 100 characters");
        }
    }

    /**
     * Validates the IBAN.
     *
     * @param iban The IBAN to validate.
     * @throws BankAccountException If the IBAN is invalid.
     */
    private static void validateIban(String iban) throws BankAccountException {
        if (iban == null || iban.trim().isEmpty()) {
            throw new BankAccountException("IBAN is required");
        }
        if (iban.length() < 15 || iban.length() > 34) {
            throw new BankAccountException("IBAN must be between 15 and 34 characters");
        }
        if (!iban.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$")) {
            throw new BankAccountException("Invalid IBAN format. It must start with a country code (e.g., FR, DE)");
        }
    }

    /**
     * Validates the BIC (SWIFT code).
     *
     * @param bic The BIC to validate.
     * @throws BankAccountException If the BIC is invalid.
     */
    private static void validateBic(String bic) throws BankAccountException {
        if (bic == null || bic.trim().isEmpty()) {
            throw new BankAccountException("BIC (SWIFT code) is required");
        }
        if (bic.length() < 8 || bic.length() > 11) {
            throw new BankAccountException("BIC must be between 8 and 11 characters");
        }
        if (!bic.matches("^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$")) {
            throw new BankAccountException("Invalid BIC format");
        }
    }
}