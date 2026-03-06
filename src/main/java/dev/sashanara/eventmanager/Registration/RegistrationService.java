package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.Event;
import dev.sashanara.eventmanager.Events.EventService;
import dev.sashanara.eventmanager.Events.EventStatus;
import dev.sashanara.eventmanager.Exeptions.EventStatusExceptions;
import dev.sashanara.eventmanager.Users.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationConverter registrationConverter;
    private final RegistrationRepository registrationRepository;
    private final EventService eventService;

    public RegistrationService(
            RegistrationConverter registrationConverter,
            RegistrationRepository registrationRepository,
            EventService eventService
    ) {
        this.registrationConverter = registrationConverter;
        this.registrationRepository = registrationRepository;
        this.eventService = eventService;
    }

    @Transactional
    public Registration registerUserForTheEvent(String token, Long eventId) {

        EventStatus eventStatus = eventService.getEventById(eventId).status();

        if (!eventStatus.equals(EventStatus.WAIT_START)) {
            throw new EventStatusExceptions("Not supported EventStatus");
        }

        Long userId = eventService.getUserIdFromToken(token);

        return registrationConverter.toDomain(
                registrationRepository.save(new RegistrationEntity(userId, eventId))
        );
    }

    @Transactional
    public void deleteUserRegistrationForTheEvent(String token, Long eventId) {

        EventStatus eventStatus = eventService.getEventById(eventId).status();

        if (eventStatus.equals(EventStatus.STARTED)
                || eventStatus.equals(EventStatus.FINISHED)) {
            throw new EventStatusExceptions("Not supported EventStatus");
        }

        Long userId = eventService.getUserIdFromToken(token);

        registrationRepository.deleteByUserIdAndEventId(userId, eventId);
    }

    public List<Event> findAllEventsByUserToken(String token) {

        Long userId = eventService.getUserIdFromToken(token);

        List<Long> eventIdsList = registrationRepository.findAllByUserId(userId);

        // todo сделать oneToMany  и доделать этот метод

        return null;
    }

}
