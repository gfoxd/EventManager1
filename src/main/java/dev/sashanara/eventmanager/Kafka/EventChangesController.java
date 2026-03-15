package dev.sashanara.eventmanager.Kafka;

import dev.sashanara.eventmanager.Kafka.EventChanges.EventChangesMessageConverter;
import dev.sashanara.eventmanager.Kafka.EventChanges.EventChangesMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventChangesController {

    private final Logger log = LoggerFactory.getLogger(EventChangesController.class);

    private final EventChangesMessageConverter eventChangesMessageConverter;
    private final KafkaEventProducerService kafkaEventProducerService;

    public EventChangesController(
            EventChangesMessageConverter eventChangesMessageConverter,
            KafkaEventProducerService kafkaEventProducerService
    ) {
        this.eventChangesMessageConverter = eventChangesMessageConverter;
        this.kafkaEventProducerService = kafkaEventProducerService;
    }

    @PostMapping("/send-event-changes")
    public ResponseEntity<EventChangesMessageDto> sendEventChanges(
            @RequestBody EventChangesMessageDto eventChangesMessageDto
    ) {
        log.info("Kafka send eventChangesMessageDto");

        kafkaEventProducerService.sendEventChanges(
                eventChangesMessageConverter.toDomain(eventChangesMessageDto)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventChangesMessageDto);
    }

}
