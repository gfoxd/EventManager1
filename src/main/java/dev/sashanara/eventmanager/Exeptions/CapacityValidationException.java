package dev.sashanara.eventmanager.Exeptions;

import jakarta.validation.ValidationException;

public class CapacityValidationException extends ValidationException {
    public CapacityValidationException(String message) {
        super(message);
    }
}
