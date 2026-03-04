package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequest;
import dev.sashanara.eventmanager.Locations.LocationRepository;
import dev.sashanara.eventmanager.Registration.Registration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    public EventService(
            EventRepository eventRepository,
            EventConverter eventConverter
    ) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
    }

    public Event createEvent(Event event) {
        return null;
    }

    public void deleteEvent(Long eventId) {

    }

    public Event getEventById(Long eventId) {
        return null;
    }

    public Event updateEvent(Long eventId, Event event) {
        return null;
    }

    public List<Event> searchEvents(EventSearchRequest eventSearchRequest) {
        return null;
    }

    public List<Event> searchEventsByUserId() {
        return null;
    }

    public Registration registerUserForTheEvent(Long userId, Long eventId) {
        return null;
    }

    public void deleteUserRegistrationForTheEvent(Long userId, Long eventId) {

    }

    public List<Event> findAllEventsByUserId(Long userId) {
        return null;
    }
}
