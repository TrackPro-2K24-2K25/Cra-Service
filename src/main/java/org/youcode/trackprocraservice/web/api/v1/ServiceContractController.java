package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;
import org.youcode.trackprocraservice.service.interfaces.ServiceContractService;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractResponseVM;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractVM;
import org.youcode.trackprocraservice.web.vm.mapper.ServiceContractMapper;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-contracts")
@Tag(name = "Service Contract", description = "APIs for managing service contracts")
public class ServiceContractController {

    private final ServiceContractService serviceContractService;
    private final ServiceContractMapper serviceContractMapper;

    public ServiceContractController(ServiceContractService serviceContractService, ServiceContractMapper serviceContractMapper) {
        this.serviceContractService = serviceContractService;
        this.serviceContractMapper = serviceContractMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new service contract", description = "Create a new service contract with the provided details")
    @ApiResponse(responseCode = "201", description = "Service contract created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<ServiceContractResponseVM> create(@RequestBody ServiceContractVM serviceContractVM) {
        ServiceContract serviceContract = serviceContractMapper.toEntity(serviceContractVM);
        ServiceContract createdServiceContract = serviceContractService.create(serviceContract);
        ServiceContractResponseVM responseVM = serviceContractMapper.toResponseVM(createdServiceContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
    }

    @GetMapping
    @Operation(summary = "Get all service contracts", description = "Retrieve a paginated list of all service contracts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of service contracts")
    public ResponseEntity<Page<ServiceContractResponseVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ServiceContract> serviceContracts = serviceContractService.findAll(page, size);
        Page<ServiceContractResponseVM> responseVMs = serviceContracts.map(serviceContractMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a service contract by ID", description = "Retrieve a service contract by its unique ID")
    @ApiResponse(responseCode = "200", description = "Service contract found")
    @ApiResponse(responseCode = "404", description = "Service contract not found")
    public ResponseEntity<ServiceContractResponseVM> findById(@PathVariable UUID id) {
        Optional<ServiceContract> serviceContract = serviceContractService.findById(id);
        return serviceContract.map(contract -> ResponseEntity.ok(serviceContractMapper.toResponseVM(contract)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a service contract", description = "Update an existing service contract by its ID")
    @ApiResponse(responseCode = "200", description = "Service contract updated successfully")
    @ApiResponse(responseCode = "404", description = "Service contract not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<ServiceContractResponseVM> update(
            @PathVariable UUID id,
            @RequestBody ServiceContractVM serviceContractVM) {
        ServiceContract serviceContract = serviceContractMapper.toEntity(serviceContractVM);
        ServiceContract updatedServiceContract = serviceContractService.update(id, serviceContract);
        ServiceContractResponseVM responseVM = serviceContractMapper.toResponseVM(updatedServiceContract);
        return ResponseEntity.ok(responseVM);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a service contract", description = "Delete a service contract by its ID")
    @ApiResponse(responseCode = "200", description = "Service contract deleted successfully")
    @ApiResponse(responseCode = "404", description = "Service contract not found")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean isDeleted = serviceContractService.delete(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}