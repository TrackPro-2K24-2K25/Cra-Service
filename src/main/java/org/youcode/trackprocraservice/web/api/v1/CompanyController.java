package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.exception.Company.CompanyException;
import org.youcode.trackprocraservice.service.interfaces.CompanyService;
import org.youcode.trackprocraservice.web.vm.Company.CompanyResponseVM;
import org.youcode.trackprocraservice.web.vm.Company.CompanyVM;
import org.youcode.trackprocraservice.web.vm.mapper.CompanyMapper;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Company Management", description = "APIs for managing companies")
@Validated
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new company", description = "Create a new company with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Company created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Company with the same name already exists")
    })
    public ResponseEntity<CompanyResponseVM> createCompany(@Valid @RequestBody CompanyVM companyVM) {
        try {
            // Convert VM to entity
            Company company = companyMapper.toEntity(companyVM);

            // Create the company
            Company createdCompany = companyService.createCompany(company);

            // Convert entity to response VM
            CompanyResponseVM responseVM = companyMapper.toResponseVM(createdCompany);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
        } catch (CompanyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a company by ID", description = "Retrieve a company by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company found"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<CompanyResponseVM> getCompanyById(@PathVariable UUID id) {
        try {
            // Get the company by ID
            Company company = companyService.getCompanyById(id);

            // Convert entity to response VM
            CompanyResponseVM responseVM = companyMapper.toResponseVM(company);

            return ResponseEntity.ok(responseVM);
        } catch (CompanyException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieve a paginated list of all companies")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Companies retrieved successfully")
    })
    public ResponseEntity<Page<CompanyResponseVM>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // Get paginated companies
        Page<Company> companies = companyService.findAll(page, size);

        // Convert entities to response VMs
        Page<CompanyResponseVM> responseVMs = companies.map(companyMapper::toResponseVM);

        return ResponseEntity.ok(responseVMs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a company", description = "Update an existing company by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<CompanyResponseVM> updateCompany(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyVM companyVM
    ) {
        try {
            // Convert VM to entity
            Company company = companyMapper.toEntity(companyVM);

            // Update the company
            Company updatedCompany = companyService.updateCompany(id, company);

            // Convert entity to response VM
            CompanyResponseVM responseVM = companyMapper.toResponseVM(updatedCompany);

            return ResponseEntity.ok(responseVM);
        } catch (CompanyException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a company", description = "Delete a company by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        boolean isDeleted = companyService.deleteCompany(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}