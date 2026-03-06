package dev.sashanara.eventmanager.Events.EventStatusManager;

import dev.sashanara.eventmanager.Events.EventEntity;
import dev.sashanara.eventmanager.Events.EventRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventStatusUpdateService {

    private final EventRepository eventRepository;

    public EventStatusUpdateService(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    public void updateEventStatuses() {
        List<EventEntity> events = eventRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for (EventEntity event : events) {
            LocalDateTime endTime = event.getDate().plusMinutes(event.getDuration());

            if (now.isAfter(endTime)
                    && !event.getStatus().equals(EventStatus.FINISHED)
                    && !event.getStatus().equals(EventStatus.CANCELLED)
            ) {
                event.setStatus(EventStatus.FINISHED);
            } else if (now.isAfter(event.getDate())
                    && now.isBefore(endTime)
                    && !event.getStatus().equals(EventStatus.STARTED)
                    && !event.getStatus().equals(EventStatus.CANCELLED)
            ) {
                event.setStatus(EventStatus.STARTED);
            }
        }
        eventRepository.saveAll(events);
    }
}