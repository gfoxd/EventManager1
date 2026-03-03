package dev.sashanara.eventmanager.Registration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record RegistrationDto(

        @Null
        Long id,

        @Null
        Long userId,

        @NotNull
        @Min(1)
        Long eventId

) {
}
