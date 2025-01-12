package org.youcode.trackprocraservice.service.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.trackprocraservice.domain.entities.File;
import org.youcode.trackprocraservice.exception.File.FileValidationException;

import java.util.Optional;
import java.util.UUID;

public interface FileService {
    File createFile(File file) throws FileValidationException;

    Page<File> findAll(int page, int size);

    Optional<File> getFileById(UUID id);

    Optional<File> updateFile(UUID id, File updatedFile) throws FileValidationException;

    boolean deleteFile(UUID id);

    Page<File> findByNameContainingIgnoreCase(String name, int page, int size);

    Page<File> findByContentType(String contentType, int page, int size);

    Page<File> findByUrl(String url, int page, int size);

    Page<File> findByTimesheetId(UUID timesheetId, int page, int size);

    Page<File> findByExpenseReportId(UUID expenseReportId, int page, int size);

    boolean deleteByTimesheetId(UUID timesheetId);

    boolean deleteByExpenseReportId(UUID expenseReportId);
}
