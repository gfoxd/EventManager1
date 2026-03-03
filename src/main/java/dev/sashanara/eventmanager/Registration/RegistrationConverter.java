package dev.sashanara.eventmanager.Registration;

import org.springframework.stereotype.Component;

@Component
public class RegistrationConverter {

    public RegistrationDto toDto(Registration registration){
        return new RegistrationDto(
                registration.id(),
                registration.userId(),
                registration.eventId()
        );
    }

    public Registration toDomain(RegistrationDto registrationDto){
        return new Registration(
                registrationDto.id(),
                registrationDto.userId(),
                registrationDto.eventId()
        );
    }

    public Registration toDomain(RegistrationEntity registrationEntity){
        return new Registration(
                registrationEntity.getId(),
                registrationEntity.getUserId(),
                registrationEntity.getEventId()
        );
    }

    public RegistrationEntity toEntity(Registration registration){
        return new RegistrationEntity(
                registration.id(),
                registration.userId(),
                registration.eventId()
        );
    }

}
