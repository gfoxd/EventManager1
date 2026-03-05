package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.Event;
import dev.sashanara.eventmanager.Events.EventConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationConverter registrationConverter;

    public RegistrationService(
            RegistrationConverter registrationConverter
    ) {
        this.registrationConverter = registrationConverter;
    }

    public Registration registerUserForTheEvent(String token, Long eventId) {

        return null;
    }

    public void deleteUserRegistrationForTheEvent(String token, Long eventId) {

    }

    public List<Event> findAllEventsByUserToken(String token) {
        return null;
    }

}
