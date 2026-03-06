package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.*;
import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import dev.sashanara.eventmanager.Exeptions.EventStatusExceptions;
import dev.sashanara.eventmanager.Exeptions.NotEnoughPlacesException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final RegistrationConverter registrationConverter;
    private final RegistrationRepository registrationRepository;
    private final EventService eventService;
    private final EventConverter eventConverter;

    public RegistrationService(
            RegistrationConverter registrationConverter,
            RegistrationRepository registrationRepository,
            EventService eventService,
            EventConverter eventConverter
    ) {
        this.registrationConverter = registrationConverter;
        this.registrationRepository = registrationRepository;
        this.eventService = eventService;
        this.eventConverter = eventConverter;
    }

    @Transactional
    public Registration registerUserForTheEvent(String token, Long eventId) {

        EventStatus eventStatus = eventService.getEventById(eventId).status();
        if (!eventStatus.equals(EventStatus.WAIT_START)) {
            throw new EventStatusExceptions("Not supported EventStatus");
        }

        Integer quantityRegistrations = eventService.getQuantityRegistrationsByEventId(eventId);
        Integer maxPlaces = eventService.getMaxPlacesByEventId(eventId);
        if (quantityRegistrations >= maxPlaces) {
            throw new NotEnoughPlacesException("Not enough Places");
        }

        Long userId = eventService.getUserIdFromToken(token);

        EventEntity eventEntity = eventConverter.toEntity(
                eventService.getEventById(eventId)
        );

        return registrationConverter.toDomain(
                registrationRepository.save(new RegistrationEntity(userId, eventEntity))
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

        List<RegistrationEntity> registrationEntityList = registrationRepository.findAllByUserId(userId);

        return registrationEntityList.stream()
                .map(registration -> eventConverter.toDomain(
                        registration.getEventEntity()
                ))
                .collect(Collectors.toList());
    }

}