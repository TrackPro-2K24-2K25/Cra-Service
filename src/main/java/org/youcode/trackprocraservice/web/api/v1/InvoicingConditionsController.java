package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.InvoicingConditions;
import org.youcode.trackprocraservice.service.interfaces.InvoicingConditionsService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoicing-conditions")
@Tag(name = "Invoicing Conditions", description = "APIs for managing invoicing conditions")
public class InvoicingConditionsController {

    private final InvoicingConditionsService invoicingConditionsService;

    public InvoicingConditionsController(InvoicingConditionsService invoicingConditionsService) {
        this.invoicingConditionsService = invoicingConditionsService;
    }

    @PostMapping
    @Operation(summary = "Create a new invoicing condition", description = "Create a new invoicing condition with the provided details")
    @ApiResponse(responseCode = "201", description = "Invoicing condition created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<InvoicingConditions> create(@RequestBody InvoicingConditions invoicingConditions) {
        InvoicingConditions createdInvoicingConditions = invoicingConditionsService.create(invoicingConditions);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoicingConditions);
    }

    @GetMapping
    @Operation(summary = "Get all invoicing conditions", description = "Retrieve a paginated list of all invoicing conditions")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of invoicing conditions")
    public ResponseEntity<Page<InvoicingConditions>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<InvoicingConditions> invoicingConditions = invoicingConditionsService.findAll(page, size);
        return ResponseEntity.ok(invoicingConditions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an invoicing condition by ID", description = "Retrieve an invoicing condition by its unique ID")
    @ApiResponse(responseCode = "200", description = "Invoicing condition found")
    @ApiResponse(responseCode = "404", description = "Invoicing condition not found")
    public ResponseEntity<InvoicingConditions> findById(@PathVariable UUID id) {
        InvoicingConditions invoicingConditions = invoicingConditionsService.findById(id);
        return ResponseEntity.ok(invoicingConditions);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an invoicing condition", description = "Update an existing invoicing condition by its ID")
    @ApiResponse(responseCode = "200", description = "Invoicing condition updated successfully")
    @ApiResponse(responseCode = "404", description = "Invoicing condition not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<InvoicingConditions> update(
            @PathVariable UUID id,
            @RequestBody InvoicingConditions updatedInvoicingConditions) {
        InvoicingConditions updatedEntity = invoicingConditionsService.update(id, updatedInvoicingConditions);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an invoicing condition", description = "Delete an invoicing condition by its ID")
    @ApiResponse(responseCode = "200", description = "Invoicing condition deleted successfully")
    @ApiResponse(responseCode = "404", description = "Invoicing condition not found")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean isDeleted = invoicingConditionsService.delete(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
