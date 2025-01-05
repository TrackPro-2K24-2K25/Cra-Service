package org.youcode.trackprocraservice.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyNotFoundException;
import org.youcode.trackprocraservice.exception.InvoicingCurrency.InvoicingCurrencyValidationException;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
