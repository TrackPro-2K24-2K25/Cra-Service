package org.youcode.trackprocraservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.trackprocraservice.domain.entities.File;
import org.youcode.trackprocraservice.exception.File.FileValidationException;
import org.youcode.trackprocraservice.repository.interfaces.FileRepository;
import org.youcode.trackprocraservice.utils.FileValidator;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements org.youcode.trackprocraservice.service.interfaces.FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File createFile(File file) throws FileValidationException {
        // Validate the file
        FileValidator.validateFile(file);

        // Check if a file with the same name already exists
        if (fileRepository.existsByName(file.getName())) {
            throw new FileValidationException("A file with the same name already exists.");
        }

        // Save the file
        return fileRepository.save(file);
    }

    @Override
    public Page<File> findAll(int page, int size) {
        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findAll(pageable);
    }

    @Override
    public Optional<File> getFileById(UUID id) {
        return fileRepository.findById(id);
    }



    @Override
    public Optional<File> updateFile(UUID id, File updatedFile) throws FileValidationException {
        // Validate the updated file
        FileValidator.validateFile(updatedFile);

        return fileRepository.findById(id).map(existingFile -> {
            // Update the file fields
            existingFile.setName(updatedFile.getName());
            existingFile.setContentType(updatedFile.getContentType());
            existingFile.setUrl(updatedFile.getUrl());

            // Save the updated file
            return fileRepository.save(existingFile);
        });
    }


    @Override
    public boolean deleteFile(UUID id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            fileRepository.delete(file.get());
            return true;
        }
        return false;
    }

    @Override
    public Page<File> findByNameContainingIgnoreCase(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<File> findByContentType(String contentType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findByContentType(contentType, pageable);
    }

    @Override
    public Page<File> findByUrl(String url, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findByUrl(url, pageable);
    }

    @Override
    public Page<File> findByTimesheetId(UUID timesheetId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findByTimesheetId(timesheetId, pageable);
    }

    @Override
    public Page<File> findByExpenseReportId(UUID expenseReportId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findByExpenseReportId(expenseReportId, pageable);
    }

    @Override
    public boolean deleteByTimesheetId(UUID timesheetId) {
        return fileRepository.deleteByTimesheetId(timesheetId);
    }

    @Override
    public boolean deleteByExpenseReportId(UUID expenseReportId) {
        return fileRepository.deleteByExpenseReportId(expenseReportId);
    }
}
