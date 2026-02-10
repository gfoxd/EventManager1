package boot.spring.eventmanager_1.Exeptions;

import java.time.LocalDateTime;

public record ServerErrorDto (
        String message,
        String detailedMessage,
        LocalDateTime dateTime
) {

}

