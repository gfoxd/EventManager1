package dev.sashanara.eventmanager.Kafka.EventChamges;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventChangesMessageConverter {

    public EventChangesMessageEntity toEntity(EventChangesMessage domain) {
        EventChangesMessageEntity entity = new EventChangesMessageEntity();

        entity.setId(domain.id());
        entity.setEventId(domain.eventId());
        entity.setChangedByUserId(domain.changedByUserId());
        entity.setOwnerId(domain.ownerId());

        if (domain.name() != null) {
            entity.setNameOldValue(domain.name().getOldValue());
            entity.setNameNewValue(domain.name().getNewValue());
        }

        if (domain.maxPlaces() != null) {
            entity.setMaxPlacesOldValue(domain.maxPlaces().getOldValue());
            entity.setMaxPlacesNewValue(domain.maxPlaces().getNewValue());
        }

        if (domain.date() != null) {
            entity.setDateOldValue(domain.date().getOldValue());
            entity.setDateNewValue(domain.date().getNewValue());
        }

        if (domain.cost() != null) {
            entity.setCostOldValue(domain.cost().getOldValue());
            entity.setCostNewValue(domain.cost().getNewValue());
        }

        if (domain.duration() != null) {
            entity.setDurationOldValue(domain.duration().getOldValue());
            entity.setDurationNewValue(domain.duration().getNewValue());
        }

        if (domain.locationId() != null) {
            entity.setLocationIdOldValue(domain.locationId().getOldValue());
            entity.setLocationIdNewValue(domain.locationId().getNewValue());
        }

        if (domain.status() != null) {
            entity.setStatusOldValue(domain.status().getOldValue());
            entity.setStatusNewValue(domain.status().getNewValue());
        }

        if (domain.RegisteredUserIds() != null) {
            entity.setRegisteredUserIds(new ArrayList<>(domain.RegisteredUserIds()));
        }

        return entity;
    }

    public EventChangesMessage toDomain(EventChangesMessageEntity entity) {
        return new EventChangesMessage(
                entity.getId(),
                entity.getEventId(),
                entity.getChangedByUserId(),
                entity.getOwnerId(),

                entity.getNameOldValue() != null && entity.getNameNewValue() != null ?
                        new FieldChange<>(entity.getNameOldValue(), entity.getNameNewValue()) : null,
                entity.getMaxPlacesOldValue() != null && entity.getMaxPlacesNewValue() != null ?
                        new FieldChange<>(entity.getMaxPlacesOldValue(), entity.getMaxPlacesNewValue()) : null,
                entity.getDateOldValue() != null && entity.getDateNewValue() != null ?
                        new FieldChange<>(entity.getDateOldValue(), entity.getDateNewValue()) : null,
                entity.getCostOldValue() != null && entity.getCostNewValue() != null ?
                        new FieldChange<>(entity.getCostOldValue(), entity.getCostNewValue()) : null,
                entity.getDurationOldValue() != null && entity.getDurationNewValue() != null ?
                        new FieldChange<>(entity.getDurationOldValue(), entity.getDurationNewValue()) : null,
                entity.getLocationIdOldValue() != null && entity.getLocationIdNewValue() != null ?
                        new FieldChange<>(entity.getLocationIdOldValue(), entity.getLocationIdNewValue()) : null,
                entity.getStatusOldValue() != null && entity.getStatusNewValue() != null ?
                        new FieldChange<>(entity.getStatusOldValue(), entity.getStatusNewValue()) : null,

                entity.getRegisteredUserIds() != null ?
                        new ArrayList<>(entity.getRegisteredUserIds()) : List.of()
        );
    }

    public EventChangesMessage toDomain(EventChangesMessageDto dto) {
        return new EventChangesMessage(
                dto.id(),
                dto.eventId(),
                dto.changedByUserId(),
                dto.ownerId(),
                dto.name(),
                dto.maxPlaces(),
                dto.date(),
                dto.cost(),
                dto.duration(),
                dto.locationId(),
                dto.status(),
                dto.RegisteredUserIds()
        );
    }

    public EventChangesMessageDto toDto(EventChangesMessage domain) {
        return new EventChangesMessageDto(
                domain.id(),
                domain.eventId(),
                domain.changedByUserId(),
                domain.ownerId(),
                domain.name(),
                domain.maxPlaces(),
                domain.date(),
                domain.cost(),
                domain.duration(),
                domain.locationId(),
                domain.status(),
                domain.RegisteredUserIds()
        );
    }

}
