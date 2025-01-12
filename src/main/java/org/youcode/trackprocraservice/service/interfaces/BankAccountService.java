package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.exception.BankAccount.BankAccountException;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountService {
    BankAccount createBankAccount(BankAccount bankAccount) throws BankAccountException;

    // Retrieve paginated BankAccounts
    Page<BankAccount> findAll(int page, int size);

    // Retrieve a BankAccount by ID
    Optional<BankAccount> getBankAccountById(UUID id);

    // Update an existing BankAccount
    Optional<BankAccount> updateBankAccount(UUID id, BankAccount updatedBankAccount) throws BankAccountException;

    // Delete a BankAccount by ID
    boolean deleteBankAccount(UUID id);
}
