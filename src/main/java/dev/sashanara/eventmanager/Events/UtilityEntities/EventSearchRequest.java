package dev.sashanara.eventmanager.Events.UtilityEntities;

import dev.sashanara.eventmanager.Events.EventStatus;

import java.time.LocalDateTime;

public record EventSearchRequest(

        String name,
        Integer minPlaces,
        Integer maxPlaces,
        LocalDateTime dateStartAfter,
        LocalDateTime dateStartBefore,
        Integer minCost,
        Integer maxCost,
        Integer minDuration,
        Integer maxDuration,
        Long locationId,
        EventStatus status

) {
}
