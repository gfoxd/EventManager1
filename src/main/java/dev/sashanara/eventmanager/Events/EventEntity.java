package dev.sashanara.eventmanager.Events;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import dev.sashanara.eventmanager.Registration.RegistrationEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "occupiedPlaces")
    private Integer occupiedPlaces;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime date;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "maxPlaces")
    private Integer maxPlaces;

    @Column(name = "locationId")
    private Long locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "ownerId")
    private Long ownerId;

    @Column(name = "status")
    private EventStatus status;

    @OneToMany(mappedBy = "eventEntity", fetch = FetchType.LAZY)
    private List<RegistrationEntity> registrations;

    public EventEntity() {
    }

    public EventEntity(
            EventStatus status,
            String name,
            Long locationId,
            Integer maxPlaces,
            Integer cost,
            Integer duration,
            OffsetDateTime date,
            Integer occupiedPlaces,
            Long ownerId
    ) {
        this.status = status;
        this.name = name;
        this.locationId = locationId;
        this.maxPlaces = maxPlaces;
        this.cost = cost;
        this.duration = duration;
        this.date = date;
        this.occupiedPlaces = occupiedPlaces;
        this.ownerId = ownerId;
    }

    public EventEntity(
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
        this.id = id;
        this.occupiedPlaces = occupiedPlaces;
        this.date = date;
        this.duration = duration;
        this.cost = cost;
        this.maxPlaces = maxPlaces;
        this.locationId = locationId;
        this.name = name;
        this.ownerId = ownerId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOccupiedPlaces() {
        return occupiedPlaces;
    }

    public void setOccupiedPlaces(Integer occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    public OffsetDateTime  getDate() {
        return date;
    }

    public void setDate(OffsetDateTime  date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(Integer maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<RegistrationEntity> getRegistrations() {
        return registrations;
    }
}
