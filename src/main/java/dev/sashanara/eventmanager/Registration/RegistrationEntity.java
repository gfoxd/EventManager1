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

}
