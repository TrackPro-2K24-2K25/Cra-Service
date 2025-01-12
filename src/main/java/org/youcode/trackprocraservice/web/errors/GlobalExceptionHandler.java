package org.youcode.trackprocraservice.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.youcode.trackprocraservice.exception.ExpenseReport.ExpenseReportInvalidOperationException;
import org.youcode.trackprocraservice.exception.ExpenseReport.ExpenseReportNotFoundException;
import org.youcode.trackprocraservice.exception.ExpenseReport.ExpenseReportValidationException;
import org.youcode.trackprocraservice.exception.File.FileNotFoundException;
import org.youcode.trackprocraservice.exception.File.FileStorageException;
import org.youcode.trackprocraservice.exception.File.FileValidationException;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyNotFoundException;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyValidationException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvoicingCurrencyNotFoundException.class)
    public ResponseEntity<String> handleInvoicingCurrencyNotFound(InvoicingCurrencyNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoicingCurrencyValidationException.class)
    public ResponseEntity<String> handleInvoicingCurrencyValidation(InvoicingCurrencyValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleFileNotFoundException(FileNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "File Not Found");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(FileValidationException.class)
    public ResponseEntity<Map<String, String>> handleFileValidationException(FileValidationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Validation Error");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<Map<String, String>> handleFileStorageException(FileStorageException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "File Storage Error");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ExpenseReportNotFoundException.class)
    public ResponseEntity<String> handleExpenseReportNotFoundException(ExpenseReportNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ExpenseReportValidationException.class)
    public ResponseEntity<String> handleExpenseReportValidationException(ExpenseReportValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ExpenseReportInvalidOperationException.class)
    public ResponseEntity<String> handleExpenseReportInvalidOperationException(ExpenseReportInvalidOperationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
