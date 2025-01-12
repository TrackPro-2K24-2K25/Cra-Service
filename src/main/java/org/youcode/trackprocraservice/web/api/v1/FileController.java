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
import org.youcode.trackprocraservice.domain.entities.File;
import org.youcode.trackprocraservice.exception.File.FileValidationException;
import org.youcode.trackprocraservice.service.interfaces.FileService;
import org.youcode.trackprocraservice.web.vm.File.FileResponseVM;
import org.youcode.trackprocraservice.web.vm.File.FileVM;
import org.youcode.trackprocraservice.web.vm.mapper.FileMapper;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "File Management", description = "APIs for managing files")
public class FileController {

    private final FileService fileService;
    private final FileMapper fileMapper;

    @Autowired
    public FileController(FileService fileService, FileMapper fileMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new file", description = "Create a new file with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "File created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "File with the same name already exists")
    })
    public ResponseEntity<FileResponseVM> createFile(@RequestBody FileVM fileVM) {
        try {
            // Convert VM to Entity
            File file = fileMapper.toEntity(fileVM);

            // Create the file
            File createdFile = fileService.createFile(file);

            // Convert Entity to Response VM
            FileResponseVM responseVM = fileMapper.toResponseVM(createdFile);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseVM);
        } catch (FileValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a file by ID", description = "Retrieve a file by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File found"),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    public ResponseEntity<FileResponseVM> getFileById(@PathVariable UUID id) {
        Optional<File> file = fileService.getFileById(id);
        return file.map(entity -> ResponseEntity.ok(fileMapper.toResponseVM(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all files", description = "Retrieve a paginated list of all files")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> getAllFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findAll(page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a file", description = "Update an existing file by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    public ResponseEntity<FileResponseVM> updateFile(
            @PathVariable UUID id,
            @RequestBody FileVM updatedFileVM
    ) {
        try {
            // Convert VM to Entity
            File updatedFile = fileMapper.toEntity(updatedFileVM);

            // Update the file
            Optional<File> file = fileService.updateFile(id, updatedFile);

            // Convert Entity to Response VM
            return file.map(entity -> ResponseEntity.ok(fileMapper.toResponseVM(entity)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (FileValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a file", description = "Delete a file by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "File deleted successfully"),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    public ResponseEntity<Void> deleteFile(@PathVariable UUID id) {
        boolean isDeleted = fileService.deleteFile(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/name")
    @Operation(summary = "Search files by name", description = "Search files by name (case-insensitive)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> findByNameContainingIgnoreCase(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findByNameContainingIgnoreCase(name, page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/search/content-type")
    @Operation(summary = "Search files by content type", description = "Search files by content type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> findByContentType(
            @RequestParam String contentType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findByContentType(contentType, page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/search/url")
    @Operation(summary = "Search files by URL", description = "Search files by URL")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> findByUrl(
            @RequestParam String url,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findByUrl(url, page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/search/timesheet/{timesheetId}")
    @Operation(summary = "Search files by timesheet ID", description = "Search files associated with a specific timesheet")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> findByTimesheetId(
            @PathVariable UUID timesheetId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findByTimesheetId(timesheetId, page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/search/expense-report/{expenseReportId}")
    @Operation(summary = "Search files by expense report ID", description = "Search files associated with a specific expense report")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files retrieved successfully")
    })
    public ResponseEntity<Page<FileResponseVM>> findByExpenseReportId(
            @PathVariable UUID expenseReportId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<File> files = fileService.findByExpenseReportId(expenseReportId, page, size);
        Page<FileResponseVM> responseVMs = files.map(fileMapper::toResponseVM);
        return ResponseEntity.ok(responseVMs);
    }

    @DeleteMapping("/timesheet/{timesheetId}")
    @Operation(summary = "Delete files by timesheet ID", description = "Delete all files associated with a specific timesheet")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files deleted successfully"),
            @ApiResponse(responseCode = "404", description = "No files found for the given timesheet ID")
    })
    public ResponseEntity<String> deleteByTimesheetId(@PathVariable UUID timesheetId) {
        boolean isDeleted = fileService.deleteByTimesheetId(timesheetId);
        if (isDeleted) {
            return ResponseEntity.ok("Files associated with timesheet ID " + timesheetId + " were deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found for timesheet ID " + timesheetId + ".");
        }
    }

    @DeleteMapping("/expense-report/{expenseReportId}")
    @Operation(summary = "Delete files by expense report ID", description = "Delete all files associated with a specific expense report")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Files deleted successfully"),
            @ApiResponse(responseCode = "404", description = "No files found for the given expense report ID")
    })
    public ResponseEntity<String> deleteByExpenseReportId(@PathVariable UUID expenseReportId) {
        boolean isDeleted = fileService.deleteByExpenseReportId(expenseReportId);
        if (isDeleted) {
            return ResponseEntity.ok("Files associated with expense report ID " + expenseReportId + " were deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found for expense report ID " + expenseReportId + ".");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    @Operation(summary = "Check if a file exists", description = "Check if a file exists by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File exists"),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    public ResponseEntity<Void> optionsFile(@PathVariable UUID id) {
        boolean exists = fileService.getFileById(id).isPresent();
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}