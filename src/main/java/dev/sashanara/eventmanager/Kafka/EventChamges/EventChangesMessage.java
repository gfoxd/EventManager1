package dev.sashanara.eventmanager.Kafka.EventChamges;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record EventChangesMessage(
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

        List<Long> registeredUserIds
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long eventId;
        private Long changedByUserId;
        private Long ownerId;
        private FieldChange<String> name;
        private FieldChange<Integer> maxPlaces;
        private FieldChange<OffsetDateTime> date;
        private FieldChange<Integer> cost;
        private FieldChange<Integer> duration;
        private FieldChange<Long> locationId;
        private FieldChange<EventStatus> status;
        private List<Long> registeredUserIds;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder eventId(Long eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder changedByUserId(Long changedByUserId) {
            this.changedByUserId = changedByUserId;
            return this;
        }

        public Builder ownerId(Long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder name(FieldChange<String> name) {
            this.name = name;
            return this;
        }

        public Builder maxPlaces(FieldChange<Integer> maxPlaces) {
            this.maxPlaces = maxPlaces;
            return this;
        }

        public Builder date(FieldChange<OffsetDateTime> date) {
            this.date = date;
            return this;
        }

        public Builder cost(FieldChange<Integer> cost) {
            this.cost = cost;
            return this;
        }

        public Builder duration(FieldChange<Integer> duration) {
            this.duration = duration;
            return this;
        }

        public Builder locationId(FieldChange<Long> locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder status(FieldChange<EventStatus> status) {
            this.status = status;
            return this;
        }

        public Builder registeredUserIds(List<Long> registeredUserIds) {
            this.registeredUserIds = registeredUserIds;
            return this;
        }

        public EventChangesMessage build() {
            return new EventChangesMessage(
                    id, eventId, changedByUserId, ownerId,
                    name, maxPlaces, date, cost, duration, locationId, status, registeredUserIds
            );
        }
    }
}
