package dev.sashanara.eventmanager.Registration;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "eventId")
    private Long eventId;

    public RegistrationEntity() {
    }

    public RegistrationEntity(
            Long id,
            Long userId,
            Long eventId
    ) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }

    public RegistrationEntity(
            Long userId,
            Long eventId
    ) {
        this.userId = userId;
        this.eventId = eventId;
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
