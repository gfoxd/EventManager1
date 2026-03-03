package dev.sashanara.eventmanager.Exeptions;

import jakarta.validation.ValidationException;

public class AgeValidationException extends ValidationException {
    public AgeValidationException(String message) {
        super(message);
    }
}