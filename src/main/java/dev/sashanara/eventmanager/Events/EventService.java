package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequest;
import dev.sashanara.eventmanager.Locations.LocationRepository;
import dev.sashanara.eventmanager.Registration.Registration;
import dev.sashanara.eventmanager.Security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final JwtUtil jwtUtil;

    public EventService(
            EventRepository eventRepository,
            EventConverter eventConverter,
            JwtUtil jwtUtil
    ) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
        this.jwtUtil = jwtUtil;
    }

    public Event createEvent(String token, Event event) {
        return null;
    }

    public void deleteEvent(Long eventId) {

    }

    public Event getEventById(Long eventId) {
        return null;
    }

    public Event updateEvent(String token, Long eventId, Event event) {
        return null;
    }

    public List<Event> searchEvents(EventSearchRequest eventSearchRequest) {
        return null;
    }

    public List<Event> searchEventsByUserToken(String token) {

        Long userId = getUserIdFromToken(token);



        return null;
    }

    public Registration registerUserForTheEvent(String token, Long eventId) {
        return null;
    }

    public void deleteUserRegistrationForTheEvent(String token, Long eventId) {

    }

    public List<Event> findAllEventsByUserToken(String token) {
        return null;
    }

    public Long getUserIdFromToken(String token) {

        jwtUtil.validateToken(token);

        return jwtUtil.getIdFromToken(token);
    }
}
