package dev.sashanara.eventmanager.Exeptions;

import jakarta.validation.ValidationException;

public class LoginAlreadyExistsException extends ValidationException {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}