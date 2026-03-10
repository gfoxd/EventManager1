package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.Event;

public record Registration(

        Long id,
        Long userId,
        Event event

) {
}
