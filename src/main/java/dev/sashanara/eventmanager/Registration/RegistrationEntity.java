package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.EventEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private EventEntity eventEntity;

    public RegistrationEntity() {
    }

    public RegistrationEntity(
            Long userId,
            EventEntity eventEntity
    ) {
        this.userId = userId;
        this.eventEntity = eventEntity;
    }

    public RegistrationEntity(
            Long id,
            Long userId,
            EventEntity eventEntity
    ) {
        this.id = id;
        this.userId = userId;
        this.eventEntity = eventEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}
