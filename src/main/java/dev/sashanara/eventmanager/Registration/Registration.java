package dev.sashanara.eventmanager.Registration;

import jakarta.persistence.Column;

public record Registration(

        Long id,

        Long userId,

        Long eventId

) {
}
