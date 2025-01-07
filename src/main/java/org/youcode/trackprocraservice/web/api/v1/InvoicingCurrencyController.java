package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;
import org.youcode.trackprocraservice.service.interfaces.InvoicingCurrencyService;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyResponseVM;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyVM;
import org.youcode.trackprocraservice.web.vm.mapper.InvoicingCurrencyMapper;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoicing-currencies")
@Tag(name = "Invoicing Currency", description = "APIs for managing invoicing currencies")
public class InvoicingCurrencyController {

    private final InvoicingCurrencyService invoicingCurrencyService;
    private final InvoicingCurrencyMapper invoicingCurrencyMapper;

    public InvoicingCurrencyController(InvoicingCurrencyService invoicingCurrencyService, InvoicingCurrencyMapper invoicingCurrencyMapper) {
        this.invoicingCurrencyService = invoicingCurrencyService;
        this.invoicingCurrencyMapper = invoicingCurrencyMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new invoicing currency", description = "Create a new invoicing currency with the provided details")
    @ApiResponse(responseCode = "201", description = "Invoicing currency created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<InvoicingCurrencyResponseVM> create(@RequestBody InvoicingCurrencyVM invoicingCurrencyVM) {
        InvoicingCurrency invoicingCurrency = invoicingCurrencyMapper.toEntity(invoicingCurrencyVM);
        InvoicingCurrency createdCurrency = invoicingCurrencyService.create(invoicingCurrency);
        InvoicingCurrencyResponseVM responseVM = invoicingCurrencyMapper.toResponseVM(createdCurrency);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
    }

    @GetMapping
    @Operation(summary = "Get all invoicing currencies", description = "Retrieve a paginated list of all invoicing currencies")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of invoicing currencies")
    public ResponseEntity<Page<InvoicingCurrencyResponseVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<InvoicingCurrency> currencies = invoicingCurrencyService.findAll(page, size);
        Page<InvoicingCurrencyResponseVM> responseVMs = currencies.map(invoicingCurrencyMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an invoicing currency", description = "Update an existing invoicing currency by its ID")
    @ApiResponse(responseCode = "200", description = "Invoicing currency updated successfully")
    @ApiResponse(responseCode = "404", description = "Invoicing currency not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<InvoicingCurrencyResponseVM> update(
            @PathVariable UUID id,
            @RequestBody InvoicingCurrencyVM invoicingCurrencyVM) {
        InvoicingCurrency updatedCurrency = invoicingCurrencyMapper.toEntity(invoicingCurrencyVM);
        updatedCurrency.setId(id); // Ensure the ID is set for the update
        InvoicingCurrency result = invoicingCurrencyService.update(updatedCurrency);
        InvoicingCurrencyResponseVM responseVM = invoicingCurrencyMapper.toResponseVM(result);
        return ResponseEntity.ok(responseVM);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an invoicing currency", description = "Delete an invoicing currency by its ID")
    @ApiResponse(responseCode = "200", description = "Invoicing currency deleted successfully")
    @ApiResponse(responseCode = "404", description = "Invoicing currency not found")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        invoicingCurrencyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}