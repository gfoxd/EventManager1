package dev.sashanara.eventmanager.Exeptions;

import jakarta.validation.ValidationException;

public class DurationValidationException extends ValidationException {
    public DurationValidationException(String message) {
        super(message);
    }
}
