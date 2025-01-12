package org.youcode.trackprocraservice.utils;

import org.youcode.trackprocraservice.domain.entities.File;
import org.youcode.trackprocraservice.exception.File.FileValidationException;

public class FileValidator {

    // Validate a File entity
    public static void validateFile(File file) throws FileValidationException {
        if (file == null) {
            throw new FileValidationException("File cannot be null");
        }

        // Validate name
        if (file.getName() == null || file.getName().trim().isEmpty()) {
            throw new FileValidationException("Name is required");
        }
        if (file.getName().length() > 255) {
            throw new FileValidationException("Name must be less than 255 characters");
        }

        // Validate content type
        if (file.getContentType() == null || file.getContentType().trim().isEmpty()) {
            throw new FileValidationException("Content type is required");
        }
        if (file.getContentType().length() > 100) {
            throw new FileValidationException("Content type must be less than 100 characters");
        }

        // Validate URL
        if (file.getUrl() == null || file.getUrl().trim().isEmpty()) {
            throw new FileValidationException("URL is required");
        }
        if (file.getUrl().length() > 500) {
            throw new FileValidationException("URL must be less than 500 characters");
        }
    }
}