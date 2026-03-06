package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;

import java.time.LocalDateTime;

public record Event(

        Long id,
        Integer occupiedPlaces,
        LocalDateTime date,
        Integer duration,
        Integer cost,
        Integer maxPlaces,
        Long locationId,
        String name,
        Long ownerId,
        EventStatus status

) {

}
