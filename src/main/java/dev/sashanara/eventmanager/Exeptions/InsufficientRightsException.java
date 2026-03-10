package dev.sashanara.eventmanager.Exeptions;

public class InsufficientRightsException extends RuntimeException {
    public InsufficientRightsException(String message) {
        super(message);
    }
}
