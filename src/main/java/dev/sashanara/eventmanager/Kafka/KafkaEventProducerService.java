package dev.sashanara.eventmanager.Kafka;

import dev.sashanara.eventmanager.Kafka.EventChanges.EventChangesMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventProducerService {

    private static final String TOPIC = "event-changes";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventProducerService(
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventChanges(EventChangesMessage eventChangesMessage) {
        kafkaTemplate.send(TOPIC, eventChangesMessage);
    }
}
