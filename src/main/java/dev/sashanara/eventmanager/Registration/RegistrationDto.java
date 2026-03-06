package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.EventDto;
import jakarta.validation.constraints.Null;

public record RegistrationDto(

        @Null
        Long id,

        @Null
        Long userId,

        @Null
        EventDto eventDto

) {
}
