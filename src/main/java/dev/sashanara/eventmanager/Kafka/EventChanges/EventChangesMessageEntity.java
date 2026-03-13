package dev.sashanara.eventmanager.Kafka.EventChanges;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "event_changes_messages")
public class EventChangesMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "changed_by_user_id", nullable = false)
    private Long changedByUserId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "name_old_value")
    private String nameOldValue;

    @Column(name = "name_new_value")
    private String nameNewValue;

    @Column(name = "max_places_old_value")
    private Integer maxPlacesOldValue;

    @Column(name = "max_places_new_value")
    private Integer maxPlacesNewValue;

    @Column(name = "date_old_value")
    private OffsetDateTime dateOldValue;

    @Column(name = "date_new_value")
    private OffsetDateTime dateNewValue;

    @Column(name = "cost_old_value")
    private Integer costOldValue;

    @Column(name = "cost_new_value")
    private Integer costNewValue;

    @Column(name = "duration_old_value")
    private Integer durationOldValue;

    @Column(name = "duration_new_value")
    private Integer durationNewValue;

    @Column(name = "location_id_old_value")
    private Long locationIdOldValue;

    @Column(name = "location_id_new_value")
    private Long locationIdNewValue;

    @Column(name = "status_old_value")
    @Enumerated(EnumType.STRING)
    private EventStatus statusOldValue;

    @Column(name = "status_new_value")
    @Enumerated(EnumType.STRING)
    private EventStatus statusNewValue;

    @ElementCollection
    @CollectionTable(
            name = "registered_user_ids",
            joinColumns = @JoinColumn(name = "event_changes_message_id")
    )
    @Column(name = "user_id")
    private List<Long> registeredUserIds;

    public EventChangesMessageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Long changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getNameOldValue() {
        return nameOldValue;
    }

    public void setNameOldValue(String nameOldValue) {
        this.nameOldValue = nameOldValue;
    }

    public String getNameNewValue() {
        return nameNewValue;
    }

    public void setNameNewValue(String nameNewValue) {
        this.nameNewValue = nameNewValue;
    }

    public Integer getMaxPlacesOldValue() {
        return maxPlacesOldValue;
    }

    public void setMaxPlacesOldValue(Integer maxPlacesOldValue) {
        this.maxPlacesOldValue = maxPlacesOldValue;
    }

    public Integer getMaxPlacesNewValue() {
        return maxPlacesNewValue;
    }

    public void setMaxPlacesNewValue(Integer maxPlacesNewValue) {
        this.maxPlacesNewValue = maxPlacesNewValue;
    }

    public OffsetDateTime getDateOldValue() {
        return dateOldValue;
    }

    public void setDateOldValue(OffsetDateTime dateOldValue) {
        this.dateOldValue = dateOldValue;
    }

    public OffsetDateTime getDateNewValue() {
        return dateNewValue;
    }

    public void setDateNewValue(OffsetDateTime dateNewValue) {
        this.dateNewValue = dateNewValue;
    }

    public Integer getCostOldValue() {
        return costOldValue;
    }

    public void setCostOldValue(Integer costOldValue) {
        this.costOldValue = costOldValue;
    }

    public Integer getCostNewValue() {
        return costNewValue;
    }

    public void setCostNewValue(Integer costNewValue) {
        this.costNewValue = costNewValue;
    }

    public Integer getDurationOldValue() {
        return durationOldValue;
    }

    public void setDurationOldValue(Integer durationOldValue) {
        this.durationOldValue = durationOldValue;
    }

    public Integer getDurationNewValue() {
        return durationNewValue;
    }

    public void setDurationNewValue(Integer durationNewValue) {
        this.durationNewValue = durationNewValue;
    }

    public Long getLocationIdOldValue() {
        return locationIdOldValue;
    }

    public void setLocationIdOldValue(Long locationIdOldValue) {
        this.locationIdOldValue = locationIdOldValue;
    }

    public Long getLocationIdNewValue() {
        return locationIdNewValue;
    }

    public void setLocationIdNewValue(Long locationIdNewValue) {
        this.locationIdNewValue = locationIdNewValue;
    }

    public EventStatus getStatusOldValue() {
        return statusOldValue;
    }

    public void setStatusOldValue(EventStatus statusOldValue) {
        this.statusOldValue = statusOldValue;
    }

    public EventStatus getStatusNewValue() {
        return statusNewValue;
    }

    public void setStatusNewValue(EventStatus statusNewValue) {
        this.statusNewValue = statusNewValue;
    }

    public List<Long> getRegisteredUserIds() {
        return registeredUserIds;
    }

    public void setRegisteredUserIds(List<Long> registeredUserIds) {
        this.registeredUserIds = registeredUserIds;
    }
}
