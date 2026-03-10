package dev.sashanara.eventmanager.Events.UtilityEntities;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record EventSearchRequest(

        String name,
        Integer minPlaces,
        Integer maxPlaces,
        OffsetDateTime dateStartAfter,
        OffsetDateTime dateStartBefore,
        Integer minCost,
        Integer maxCost,
        Integer minDuration,
        Integer maxDuration,
        Long locationId,
        EventStatus status

) {
}
