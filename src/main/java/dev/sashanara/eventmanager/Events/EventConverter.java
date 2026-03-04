package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequest;
import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequestDto;
import org.springframework.stereotype.Component;

@Component
public class EventConverter {

    public EventDto toDto(Event event) {
        return new EventDto(
                event.id(),
                event.occupiedPlaces(),
                event.date(),
                event.duration(),
                event.cost(),
                event.maxPlaces(),
                event.locationId(),
                event.name(),
                event.ownerId(),
                event.status()
        );
    }

    public Event toDomain(EventDto eventDto) {
        return new Event(
                eventDto.id(),
                eventDto.occupiedPlaces(),
                eventDto.date(),
                eventDto.duration(),
                eventDto.cost(),
                eventDto.maxPlaces(),
                eventDto.locationId(),
                eventDto.name(),
                eventDto.ownerId(),
                eventDto.status()
        );
    }

    public Event toDomain(EventEntity eventEntity) {
        return new Event(
                eventEntity.getId(),
                eventEntity.getOccupiedPlaces(),
                eventEntity.getDate(),
                eventEntity.getDuration(),
                eventEntity.getCost(),
                eventEntity.getMaxPlaces(),
                eventEntity.getLocationId(),
                eventEntity.getName(),
                eventEntity.getOwnerId(),
                eventEntity.getStatus()
        );
    }

    public EventSearchRequest toDomain(EventSearchRequestDto eventSearchRequestDto) {
        return new EventSearchRequest(
                eventSearchRequestDto.name(),
                eventSearchRequestDto.minPlaces(),
                eventSearchRequestDto.maxPlaces(),
                eventSearchRequestDto.dateStartAfter(),
                eventSearchRequestDto.dateStartBefore(),
                eventSearchRequestDto.minCost(),
                eventSearchRequestDto.maxCost(),
                eventSearchRequestDto.minDuration(),
                eventSearchRequestDto.maxDuration(),
                eventSearchRequestDto.locationId(),
                eventSearchRequestDto.status()
        );
    }

    public EventEntity toEntity(Event event) {
        return new EventEntity(
                event.id(),
                event.occupiedPlaces(),
                event.date(),
                event.duration(),
                event.cost(),
                event.maxPlaces(),
                event.locationId(),
                event.name(),
                event.ownerId(),
                event.status()
        );
    }

}
