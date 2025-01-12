package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.exception.BankAccount.BankAccountException;
import org.youcode.trackprocraservice.service.interfaces.BankAccountService;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountResponseVM;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountVM;
import org.youcode.trackprocraservice.web.vm.mapper.BankAccountMapper;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bank-accounts")
@Tag(name = "Bank Account Management", description = "APIs for managing bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new bank account", description = "Create a new bank account with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bank account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Bank account with the same account number or IBAN already exists")
    })
    public ResponseEntity<BankAccountResponseVM> createBankAccount(@RequestBody BankAccountVM bankAccountVM) {
        try {
            // Convert VM to Entity
            BankAccount bankAccount = bankAccountMapper.toEntity(bankAccountVM);

            // Create the bank account
            BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);

            // Convert Entity to Response VM
            BankAccountResponseVM responseVM = bankAccountMapper.toResponseVM(createdBankAccount);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
        } catch (BankAccountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a bank account by ID", description = "Retrieve a bank account by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank account found"),
            @ApiResponse(responseCode = "404", description = "Bank account not found")
    })
    public ResponseEntity<BankAccountResponseVM> getBankAccountById(@PathVariable UUID id) {
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountById(id);
        return bankAccount.map(entity -> ResponseEntity.ok(bankAccountMapper.toResponseVM(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all bank accounts", description = "Retrieve a paginated list of all bank accounts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank accounts retrieved successfully")
    })
    public ResponseEntity<Page<BankAccountResponseVM>> getAllBankAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<BankAccount> bankAccounts = bankAccountService.findAll(page, size);
        Page<BankAccountResponseVM> responseVMs = bankAccounts.map(bankAccountMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bank account", description = "Update an existing bank account by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank account updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bank account not found")
    })
    public ResponseEntity<BankAccountResponseVM> updateBankAccount(
            @PathVariable UUID id,
            @RequestBody BankAccountVM updatedBankAccountVM
    ) {
        try {
            // Convert VM to Entity
            BankAccount updatedBankAccount = bankAccountMapper.toEntity(updatedBankAccountVM);

            // Update the bank account
            Optional<BankAccount> bankAccount = bankAccountService.updateBankAccount(id, updatedBankAccount);

            // Convert Entity to Response VM
            return bankAccount.map(entity -> ResponseEntity.ok(bankAccountMapper.toResponseVM(entity)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (BankAccountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bank account", description = "Delete a bank account by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bank account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bank account not found")
    })
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id) {
        boolean isDeleted = bankAccountService.deleteBankAccount(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    @Operation(summary = "Check if a bank account exists", description = "Check if a bank account exists by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank account exists"),
            @ApiResponse(responseCode = "404", description = "Bank account not found")
    })
    public ResponseEntity<Void> optionsBankAccount(@PathVariable UUID id) {
        boolean exists = bankAccountService.getBankAccountById(id).isPresent();
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}