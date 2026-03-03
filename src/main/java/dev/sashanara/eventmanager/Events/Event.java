package dev.sashanara.eventmanager.Events;

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
