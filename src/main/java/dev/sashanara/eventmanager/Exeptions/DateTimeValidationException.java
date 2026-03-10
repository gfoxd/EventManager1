package dev.sashanara.eventmanager.Exeptions;

import jakarta.validation.ValidationException;

public class DateTimeValidationException extends ValidationException {
    public DateTimeValidationException(String message) {
        super(message);
    }
}
