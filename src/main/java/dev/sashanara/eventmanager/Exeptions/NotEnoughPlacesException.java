package dev.sashanara.eventmanager.Exeptions;

public class NotEnoughPlacesException extends RuntimeException {
    public NotEnoughPlacesException(String message) {
        super(message);
    }
}
