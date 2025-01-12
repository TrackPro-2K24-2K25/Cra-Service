package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.exception.BankAccount.BankAccountException;
import org.youcode.trackprocraservice.repository.interfaces.BankAccountRepository;
import org.youcode.trackprocraservice.service.interfaces.BankAccountService;
import org.youcode.trackprocraservice.utils.BankAccountValidator;

import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) throws BankAccountException {
        // Validate the bank account
        BankAccountValidator.validateBankAccount(bankAccount);

        // Check if the account number already exists
        if (bankAccountRepository.existsByAccountNumber(bankAccount.getAccountNumber())) {
            throw new BankAccountException("Bank account with the same account number already exists.");
        }

        // Check if the IBAN already exists
        if (bankAccountRepository.existsByIban(bankAccount.getIban())) {
            throw new BankAccountException("Bank account with the same IBAN already exists.");
        }

        // Save the bank account
        return bankAccountRepository.save(bankAccount);
    }

    // Retrieve paginated BankAccounts
    @Override
    public Page<BankAccount> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountRepository.findAll(pageable);
    }

    // Retrieve a BankAccount by ID
    @Override
    public Optional<BankAccount> getBankAccountById(UUID id) {
        return bankAccountRepository.findById(id);
    }

    // Update an existing BankAccount
    @Override
    public Optional<BankAccount> updateBankAccount(UUID id, BankAccount updatedBankAccount) throws BankAccountException {
        return bankAccountRepository.findById(id).map(existingBankAccount -> {
            // Validate the updated bank account
            BankAccountValidator.validateBankAccount(updatedBankAccount);

            // Update the bank account fields
            existingBankAccount.setAccountNumber(updatedBankAccount.getAccountNumber());
            existingBankAccount.setBankName(updatedBankAccount.getBankName());
            existingBankAccount.setIban(updatedBankAccount.getIban());
            existingBankAccount.setBic(updatedBankAccount.getBic());

            // Save the updated bank account
            return bankAccountRepository.save(existingBankAccount);
        });
    }

    // Delete a BankAccount by ID
    @Override
    public boolean deleteBankAccount(UUID id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()) {
            bankAccountRepository.delete(bankAccount.get());
            return true;
        }
        return false;
    }
}