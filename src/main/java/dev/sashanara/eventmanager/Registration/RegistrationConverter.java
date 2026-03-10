package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.EventConverter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConverter {

    private final EventConverter eventConverter;

    public RegistrationConverter(EventConverter eventConverter) {
        this.eventConverter = eventConverter;
    }

    public RegistrationDto toDto(Registration registration) {
        return new RegistrationDto(
                registration.id(),
                registration.userId(),
                eventConverter.toDto(registration.event())
        );
    }

    public Registration toDomain(RegistrationDto registrationDto) {
        return new Registration(
                registrationDto.id(),
                registrationDto.userId(),
                eventConverter.toDomain(registrationDto.eventDto())
        );
    }

    public Registration toDomain(RegistrationEntity registrationEntity) {
        return new Registration(
                registrationEntity.getId(),
                registrationEntity.getUserId(),
                eventConverter.toDomain(registrationEntity.getEventEntity())
        );
    }

    public RegistrationEntity toEntity(Registration registration) {
        return new RegistrationEntity(
                registration.id(),
                registration.userId(),
                eventConverter.toEntity(registration.event())
        );
    }
}
