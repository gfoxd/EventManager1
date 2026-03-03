package dev.sashanara.eventmanager.Events;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record EventDto (

        @Null
        Long id,

        @Null
        Integer occupiedPlaces,

        @NotNull
        LocalDateTime date,

        @NotNull
        @Min(30)
        Integer duration,

        @NotNull
        @Min(0)
        Integer cost,

        @NotNull
        @Min(0)
        Integer maxPlaces,

        @NotNull
        @Min(0)
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
