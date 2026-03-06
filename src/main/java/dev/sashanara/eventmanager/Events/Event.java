package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;

import java.time.OffsetDateTime;

public record Event(

        Long id,
        Integer occupiedPlaces,
        OffsetDateTime date,
        Integer duration,
        Integer cost,
        Integer maxPlaces,
        Long locationId,
        String name,
        Long ownerId,
        EventStatus status

) {

}
