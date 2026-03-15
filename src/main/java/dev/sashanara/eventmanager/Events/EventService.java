package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequest;
import dev.sashanara.eventmanager.Exeptions.*;
import dev.sashanara.eventmanager.Kafka.EventChanges.EventChangesMessage;
import dev.sashanara.eventmanager.Kafka.EventChanges.FieldChange;
import dev.sashanara.eventmanager.Kafka.KafkaEventProducerService;
import dev.sashanara.eventmanager.Locations.Location;
import dev.sashanara.eventmanager.Locations.LocationService;
import dev.sashanara.eventmanager.Registration.RegistrationEntity;
import dev.sashanara.eventmanager.Security.JwtUtil;
import dev.sashanara.eventmanager.Users.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final LocationService locationService;
    private final KafkaEventProducerService kafkaEventProducerService;
    private final JwtUtil jwtUtil;

    public EventService(
            EventRepository eventRepository,
            EventConverter eventConverter,
            LocationService locationService,
            KafkaEventProducerService kafkaEventProducerService,
            JwtUtil jwtUtil
    ) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
        this.locationService = locationService;
        this.kafkaEventProducerService = kafkaEventProducerService;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public Event createEvent(String token, Event event) {

        if (event.duration() < 30){
            throw new DurationValidationException("Event duration should be at least 30");
        }

        if (event.date().isBefore(OffsetDateTime.now())){
            throw new DateTimeValidationException("The event must be in the future");
        }

        Location location = locationService.getLocationById(
                event.locationId());

        if (event.maxPlaces() > location.capacity()) {
            throw new CapacityValidationException("The location has to be less than the maximum number of places");
        }

        EventEntity eventEntityToSave = eventConverter.toEntity(event);

        eventEntityToSave.setOwnerId(getUserIdFromToken(token));
        eventEntityToSave.setStatus(EventStatus.WAIT_START);

        eventEntityToSave = eventRepository.save(eventEntityToSave);

        return eventConverter.toDomain(eventEntityToSave);
    }

    @Transactional
    public void deleteEvent(Long eventId, String token) {

        if (!ownerOrAdmin(token, eventId)) {
            throw new InsufficientRightsException("insufficient rights");
        }

        EventStatus eventStatus = getEventById(eventId).status();
        if (!eventStatus.equals(EventStatus.WAIT_START)) {
            throw new EventStatusExceptions("Not supported EventStatus");
        }

        eventRepository.cancelEventById(eventId, EventStatus.CANCELLED);

        kafkaEventProducerService.sendEventChanges(
                EventChangesMessage.builder()
                        .eventId(eventId)
                        .changedByUserId(getUserIdFromToken(token))
                        .ownerId(getOwnerIdByEventId(eventId))

                        .status(new FieldChange<>(EventStatus.WAIT_START,EventStatus.CANCELLED))

                        .registeredUserIds(
                                getRegisteredUserIdsByEventId(eventId)
                        )
                        .build()
        );
    }

    public Event getEventById(Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new EntityNotFoundException("Event with id " + eventId + " does not exist");
        }

        return eventConverter.toDomain(
                eventRepository.getById(eventId)
        );
    }

    @Transactional
    public Event updateEvent(String token, Long eventId, Event updateEvent) {

        if (!ownerOrAdmin(token, eventId)) {
            throw new InsufficientRightsException("insufficient rights");
        }

        Integer maxPlacesNow = eventRepository.getById(eventId).getMaxPlaces();
        if (maxPlacesNow < updateEvent.maxPlaces()) {
            throw new maxPlacesDuringUpdateException("Not enough places");
        }

        Event noneUpdateEvent = getEventById(eventId);

        kafkaEventProducerService.sendEventChanges(
                EventChangesMessage.builder()
                        .eventId(eventId)
                        .changedByUserId(getUserIdFromToken(token))
                        .ownerId(getOwnerIdByEventId(eventId))

                        .date(new FieldChange<>(noneUpdateEvent.date(), updateEvent.date()))
                        .duration(new FieldChange<>(noneUpdateEvent.duration(), updateEvent.duration()))
                        .cost(new FieldChange<>(noneUpdateEvent.cost(), updateEvent.cost()))
                        .maxPlaces(new FieldChange<>(noneUpdateEvent.maxPlaces(), updateEvent.maxPlaces()))
                        .locationId(new FieldChange<>(noneUpdateEvent.locationId(), updateEvent.locationId()))
                        .name(new FieldChange<>(noneUpdateEvent.name(), updateEvent.name()))

                        .registeredUserIds(
                                getRegisteredUserIdsByEventId(eventId)
                        )
                        .build()
        );

        eventRepository.updateEvent(
                eventId,
                updateEvent.date(),
                updateEvent.duration(),
                updateEvent.cost(),
                updateEvent.maxPlaces(),
                updateEvent.locationId(),
                updateEvent.name()
        );

        return eventConverter.toDomain(
                eventRepository.getById(eventId)
        );
    }

    public List<Event> searchEvents(EventSearchRequest eventSearchRequest) {

        List<EventEntity> eventEntityList = eventRepository.findEventsBySearchRequest(
                eventSearchRequest.name(),
                eventSearchRequest.minPlaces(),
                eventSearchRequest.maxPlaces(),
                eventSearchRequest.dateStartAfter(),
                eventSearchRequest.dateStartBefore(),
                eventSearchRequest.minCost(),
                eventSearchRequest.maxCost(),
                eventSearchRequest.minDuration(),
                eventSearchRequest.maxDuration(),
                eventSearchRequest.locationId(),
                eventSearchRequest.status()
        );

        if (eventEntityList.isEmpty()) {
            throw new EntityNotFoundException("No events found");
        }

        return eventEntityList.stream()
                .map(eventConverter::toDomain)
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsByUserToken(String token) {

        Long ownerId = getUserIdFromToken(token);

        List<EventEntity> eventEntityList = eventRepository.getEventsByOwnerId(ownerId);

        if (eventEntityList.isEmpty()) {
            throw new EntityNotFoundException("No events found");
        }

        return eventEntityList.stream()
                .map(eventConverter::toDomain)
                .collect(Collectors.toList());
    }

    public boolean ownerOrAdmin(String token, Long eventId) {

        Long userId = getUserIdFromToken(token);

        if (!eventRepository.existsById(eventId)) {
            throw new EntityNotFoundException("Event with id " + eventId + " does not exist");
        }
        EventEntity eventEntity = eventRepository.getById(eventId);

        boolean isOwner = eventEntity.getOwnerId().equals(userId);
        boolean isAdmin = getUserRoleFromToken(token).equals(Role.ADMIN);

        return isOwner || isAdmin;
    }

    public Long getUserIdFromToken(String token) {

        jwtUtil.validateToken(token);

        return jwtUtil.getIdFromToken(token);
    }

    public Role getUserRoleFromToken(String token) {
        jwtUtil.validateToken(token);

        return jwtUtil.getRoleFromToken(token);
    }

    public Integer getQuantityRegistrationsByEventId(Long eventId) {
        EventEntity eventEntity = eventRepository.getById(eventId);

        return eventEntity.getRegistrations().size();
    }

    public Integer getMaxPlacesByEventId(Long eventId) {
        EventEntity eventEntity = eventRepository.getById(eventId);

        return eventEntity.getMaxPlaces();
    }

    public Long getOwnerIdByEventId(Long eventId) {
        EventEntity eventEntity = eventRepository.getById(eventId);

        return eventEntity.getOwnerId();
    }

    public List<Long> getRegisteredUserIdsByEventId(Long eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        return eventEntity.getRegistrations()
                .stream()
                .map(RegistrationEntity::getUserId)
                .collect(Collectors.toList());
    }

}
