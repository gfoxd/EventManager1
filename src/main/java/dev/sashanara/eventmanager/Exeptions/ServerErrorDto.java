package dev.sashanara.eventmanager.Exeptions;

import java.time.LocalDateTime;

public record ServerErrorDto (
        String message,
        String detailedMessage,
        LocalDateTime dateTime
) {

}

