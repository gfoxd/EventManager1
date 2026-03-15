package dev.sashanara.eventmanager.Kafka.EventChanges;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record EventChangesMessageDto (

        Long id,

        Long eventId,
        Long changedByUserId,
        Long ownerId,

        FieldChange<String> name,
        FieldChange<Integer> maxPlaces,
        FieldChange<OffsetDateTime> date,
        FieldChange<Integer> cost,
        FieldChange<Integer> duration,
        FieldChange<Long> locationId,
        FieldChange<EventStatus> status,

        List<Long> RegisteredUserIds
) {

}