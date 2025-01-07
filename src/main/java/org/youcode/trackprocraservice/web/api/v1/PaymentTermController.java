package org.youcode.trackprocraservice.web.api.v1;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;
import org.youcode.trackprocraservice.service.interfaces.PaymentTermService;
import org.youcode.trackprocraservice.web.vm.PaymentTerm.PaymentTermResponseVM;
import org.youcode.trackprocraservice.web.vm.PaymentTerm.PaymentTermVM;
import org.youcode.trackprocraservice.web.vm.mapper.PaymentTermMapper;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment-terms")
@Tag(name = "Payment Term", description = "APIs for managing payment terms")
public class PaymentTermController {

    private final PaymentTermService paymentTermService;
    private final PaymentTermMapper paymentTermMapper;

    public PaymentTermController(PaymentTermService paymentTermService, PaymentTermMapper paymentTermMapper) {
        this.paymentTermService = paymentTermService;
        this.paymentTermMapper = paymentTermMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new payment term", description = "Create a new payment term with the provided details")
    @ApiResponse(responseCode = "201", description = "Payment term created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<PaymentTermResponseVM> create(@RequestBody PaymentTermVM paymentTermVM) {
        PaymentTerm paymentTerm = paymentTermMapper.toEntity(paymentTermVM);
        PaymentTerm createdPaymentTerm = paymentTermService.createPaymentTerm(paymentTerm);
        PaymentTermResponseVM responseVM = paymentTermMapper.toResponseVM(createdPaymentTerm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
    }

    @GetMapping
    @Operation(summary = "Get all payment terms", description = "Retrieve a paginated list of all payment terms")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of payment terms")
    public ResponseEntity<Page<PaymentTermResponseVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PaymentTerm> paymentTerms = paymentTermService.findAll(page, size);
        Page<PaymentTermResponseVM> responseVMs = paymentTerms.map(paymentTermMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a payment term by ID", description = "Retrieve a payment term by its unique ID")
    @ApiResponse(responseCode = "200", description = "Payment term found")
    @ApiResponse(responseCode = "404", description = "Payment term not found")
    public ResponseEntity<PaymentTermResponseVM> findById(@PathVariable UUID id) {
        Optional<PaymentTerm> paymentTerm = paymentTermService.getPaymentTermById(id);
        return paymentTerm.map(term -> ResponseEntity.ok(paymentTermMapper.toResponseVM(term)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a payment term", description = "Update an existing payment term by its ID")
    @ApiResponse(responseCode = "200", description = "Payment term updated successfully")
    @ApiResponse(responseCode = "404", description = "Payment term not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<PaymentTermResponseVM> update(
            @PathVariable UUID id,
            @RequestBody PaymentTermVM paymentTermVM) {
        PaymentTerm paymentTerm = paymentTermMapper.toEntity(paymentTermVM);
        paymentTerm.setId(id); // Ensure the ID is set for the update
        Optional<PaymentTerm> updatedPaymentTerm = paymentTermService.updatePaymentTerm(id, paymentTerm);
        return updatedPaymentTerm.map(term -> ResponseEntity.ok(paymentTermMapper.toResponseVM(term)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a payment term", description = "Delete a payment term by its ID")
    @ApiResponse(responseCode = "200", description = "Payment term deleted successfully")
    @ApiResponse(responseCode = "404", description = "Payment term not found")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean isDeleted = paymentTermService.deletePaymentTerm(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
