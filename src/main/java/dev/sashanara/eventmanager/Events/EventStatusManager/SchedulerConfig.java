package dev.sashanara.eventmanager.Events.EventStatusManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final EventStatusUpdateService eventStatusUpdateService;

    Logger log = LoggerFactory.getLogger(SchedulerConfig.class);

    public SchedulerConfig(
            EventStatusUpdateService eventStatusUpdateService
    ) {
        this.eventStatusUpdateService = eventStatusUpdateService;
    }

    @Scheduled(fixedRate = 60000)
    public void scheduleUpdateEventStatuses() {
        log.info("Scheduling update events status");

        eventStatusUpdateService.updateEventStatuses();
    }

}