package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.Mission;
import org.youcode.trackprocraservice.exception.Mission.MissionException;
import org.youcode.trackprocraservice.service.interfaces.MissionService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/missions")
@Tag(name = "Mission Controller", description = "APIs for managing missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    @Operation(summary = "Create a new mission", description = "Creates a new mission with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission created successfully",
                    content = @Content(schema = @Schema(implementation = Mission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<Mission> createMission(
            @Parameter(description = "Mission details", required = true)
            @Valid @RequestBody Mission mission) throws MissionException {
        Mission createdMission = missionService.createMission(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a mission by ID", description = "Retrieves a mission by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission found",
                    content = @Content(schema = @Schema(implementation = Mission.class))),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<Mission> getMissionById(
            @Parameter(description = "ID of the mission to retrieve", required = true)
            @PathVariable UUID id) {
        Optional<Mission> mission = missionService.getMissionById(id);
        return mission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all missions", description = "Retrieves a paginated list of missions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getAllMissions(
            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getAllMissions(page, size);
        return ResponseEntity.ok(missions);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a mission", description = "Updates an existing mission with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission updated successfully",
                    content = @Content(schema = @Schema(implementation = Mission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<Mission> updateMission(
            @Parameter(description = "ID of the mission to update", required = true)
            @PathVariable UUID id,

            @Parameter(description = "Updated mission details", required = true)
            @Valid @RequestBody Mission updatedMission) throws MissionException {
        Mission mission = missionService.updateMission(id, updatedMission);
        return ResponseEntity.ok(mission);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a mission", description = "Deletes a mission by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<Void> deleteMission(
            @Parameter(description = "ID of the mission to delete", required = true)
            @PathVariable UUID id) {
        boolean isDeleted = missionService.deleteMission(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    @Operation(summary = "Search missions by title", description = "Searches missions by title (case-insensitive)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByTitle(
            @Parameter(description = "Title to search for", required = true)
            @RequestParam String title,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByTitle(title, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/reference")
    @Operation(summary = "Search missions by reference", description = "Searches missions by reference")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByReference(
            @Parameter(description = "Reference to search for", required = true)
            @RequestParam String reference,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByReference(reference, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/company")
    @Operation(summary = "Search missions by company ID", description = "Searches missions by company ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByCompanyId(
            @Parameter(description = "Company ID to search for", required = true)
            @RequestParam UUID companyId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByCompanyId(companyId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/supplier-admin")
    @Operation(summary = "Search missions by supplier admin ID", description = "Searches missions by supplier admin ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsBySupplierAdminId(
            @Parameter(description = "Supplier admin ID to search for", required = true)
            @RequestParam UUID supplierAdminId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsBySupplierAdminId(supplierAdminId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/collaborateur")
    @Operation(summary = "Search missions by collaborateur ID", description = "Searches missions by collaborateur ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByCollaborateurId(
            @Parameter(description = "Collaborateur ID to search for", required = true)
            @RequestParam UUID collaborateurId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByCollaborateurId(collaborateurId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/payment-term")
    @Operation(summary = "Search missions by payment term ID", description = "Searches missions by payment term ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByPaymentTermId(
            @Parameter(description = "Payment term ID to search for", required = true)
            @RequestParam UUID paymentTermId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByPaymentTermId(paymentTermId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/bank-account")
    @Operation(summary = "Search missions by bank account ID", description = "Searches missions by bank account ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByBankAccountId(
            @Parameter(description = "Bank account ID to search for", required = true)
            @RequestParam UUID bankAccountId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByBankAccountId(bankAccountId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/invoicing-condition")
    @Operation(summary = "Search missions by invoicing condition ID", description = "Searches missions by invoicing condition ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByInvoicingConditionId(
            @Parameter(description = "Invoicing condition ID to search for", required = true)
            @RequestParam UUID invoicingConditionId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByInvoicingConditionId(invoicingConditionId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/service-contract")
    @Operation(summary = "Search missions by service contract ID", description = "Searches missions by service contract ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByServiceContractId(
            @Parameter(description = "Service contract ID to search for", required = true)
            @RequestParam UUID serviceContractId,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByServiceContractId(serviceContractId, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/non-renewable")
    @Operation(summary = "Search missions by non-renewable flag", description = "Searches missions by non-renewable flag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByNonRenewable(
            @Parameter(description = "Non-renewable flag to search for", required = true)
            @RequestParam boolean nonRenewable,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByNonRenewable(nonRenewable, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/final-client")
    @Operation(summary = "Search missions by final client flag", description = "Searches missions by final client flag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByFinalClient(
            @Parameter(description = "Final client flag to search for", required = true)
            @RequestParam boolean finalClient,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByFinalClient(finalClient, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/invoice-recipient")
    @Operation(summary = "Search missions by invoice recipient flag", description = "Searches missions by invoice recipient flag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByInvoiceRecipient(
            @Parameter(description = "Invoice recipient flag to search for", required = true)
            @RequestParam boolean invoiceRecipient,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByInvoiceRecipient(invoiceRecipient, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/date-range")
    @Operation(summary = "Search missions by date range", description = "Searches missions by start and end date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByDateRange(
            @Parameter(description = "Start date of the range", required = true)
            @RequestParam Date startDate,

            @Parameter(description = "End date of the range", required = true)
            @RequestParam Date endDate,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByDateRange(startDate, endDate, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/fees-greater-than")
    @Operation(summary = "Search missions by fees greater than", description = "Searches missions with fees greater than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByFeesGreaterThan(
            @Parameter(description = "Minimum fees value", required = true)
            @RequestParam Double fees,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByFeesGreaterThan(fees, page, size);
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/search/fees-less-than")
    @Operation(summary = "Search missions by fees less than", description = "Searches missions with fees less than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<Mission>> getMissionsByFeesLessThan(
            @Parameter(description = "Maximum fees value", required = true)
            @RequestParam Double fees,

            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of missions per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getMissionsByFeesLessThan(fees, page, size);
        return ResponseEntity.ok(missions);
    }
}