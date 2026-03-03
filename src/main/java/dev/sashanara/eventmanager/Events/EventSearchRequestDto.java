package dev.sashanara.eventmanager.Events;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record EventSearchRequestDto (

        @Size(min = 5, max = 150)
        String name,

        @Min(0)
        Integer minPlaces,

        @Min(1)
        Integer maxPlaces,

        LocalDateTime dateStartAfter,

        LocalDateTime dateStartBefore,

        @Min(0)
        Integer minCost,

        @Min(0)
        Integer maxCost,

        @Min(0)
        Integer minDuration,

        @Min(0)
        Integer maxDuration,

        @Min(0)
        Long locationId,

        EventStatus status

) {

}