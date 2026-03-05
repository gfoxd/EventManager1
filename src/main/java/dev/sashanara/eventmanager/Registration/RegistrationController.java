package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.Event;
import dev.sashanara.eventmanager.Events.EventConverter;
import dev.sashanara.eventmanager.Events.EventDto;
import dev.sashanara.eventmanager.Events.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(name = "/events/registrations")
public class RegistrationController {

    Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationConverter registrationConverter;
    private final RegistrationService registrationService;
    private final EventConverter eventConverter;

    public RegistrationController(
            RegistrationConverter registrationConverter,
            RegistrationService registrationService,
            EventConverter eventConverter
    ) {
        this.registrationConverter = registrationConverter;
        this.registrationService = registrationService;
        this.eventConverter = eventConverter;
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<RegistrationDto> createUserRegistrationForTheEvent(
            @PathVariable Long eventId,
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("EventController request for user registration for the event");

        RegistrationDto registrationDto = registrationConverter.toDto(
                registrationService.registerUserForTheEvent(token, eventId)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationDto);
    }

    @DeleteMapping("/cancel/{eventId}")
    public ResponseEntity deleteUserRegistrationForTheEvent(
            @PathVariable Long eventId,
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("EventController request for user Cancel registration for the event");

        registrationService.deleteUserRegistrationForTheEvent(token, eventId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<EventDto>> getMyRegistrations(
            @RequestHeader(name = "Authorization") String token
    ){

        log.info("EventController request to get events for a specific user");

        List<Event> eventList = registrationService.findAllEventsByUserToken(token);

        List<EventDto> eventDtoList = eventList.stream()
                .map(eventConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtoList);
    }

}
