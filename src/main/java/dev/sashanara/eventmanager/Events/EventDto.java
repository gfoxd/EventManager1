package dev.sashanara.eventmanager.Events;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;

public record EventDto (

        @Null
        Long id,

        @Null
        Integer occupiedPlaces,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        OffsetDateTime date,

        @NotNull
        @Min(1)
        Integer duration,

        @NotNull
        @Min(0)
        Integer cost,

        @NotNull
        @Min(1)
        Integer maxPlaces,

        @NotNull
        @Min(1)
        Long locationId,

        @NotBlank
        @Size(min = 5, max = 150)
        String name,

        @Null
        Long ownerId,

        @Null
        EventStatus status

) {

}
