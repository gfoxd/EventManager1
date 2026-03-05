package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.UtilityEntities.EventSearchRequestDto;
import dev.sashanara.eventmanager.Registration.RegistrationConverter;
import dev.sashanara.eventmanager.Registration.RegistrationDto;
import dev.sashanara.eventmanager.Security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    private final EventConverter eventConverter;
    private final EventService eventService;

    public EventController(
            EventConverter eventConverter,
            EventService eventService
    ) {
        this.eventConverter = eventConverter;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(
            @RequestBody EventDto eventDto,
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("EventController request to create event");

        EventDto createdEventDto = eventConverter.toDto(
                eventService.createEvent(
                        token,
                        eventConverter.toDomain(eventDto)
        ));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEventDto);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity deleteEvent(
            @PathVariable Long eventId,
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("EventController request to delete event");

        eventService.deleteEvent(eventId, token);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventById(
            @PathVariable Long eventId
    ) {
        log.info("EventController request to get event by id");

        EventDto eventDto = eventConverter.toDto(
                eventService.getEventById(eventId)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDto);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDto> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventDto eventDto,
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("EventController request to update event by id");
        
        EventDto updatedEvent = eventConverter.toDto(
                eventService.updateEvent(
                        token,
                        eventId,
                        eventConverter.toDomain(eventDto)
                ));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedEvent);
    }

    @PostMapping("/search")
    public ResponseEntity< List <EventDto> > searchEvent(
            @RequestBody EventSearchRequestDto eventSearchRequestDto
    ) {
        log.info("EventController request to search event");

        List<Event> eventList = eventService.searchEvents(
                eventConverter.toDomain(eventSearchRequestDto)
        );

        List<EventDto> eventDtoList = eventList.stream()
                .map(eventConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtoList);
    }

    @GetMapping("/my")
    public ResponseEntity< List <EventDto>> getMyEvents(
            @RequestHeader(name = "Authorization") String token
    ){
        log.info("EventController request to get events for a specific user");

        List<Event> eventList = eventService.searchEventsByUserToken(token);

        List<EventDto> eventDtoList = eventList.stream()
                .map(eventConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtoList);
    }

}
