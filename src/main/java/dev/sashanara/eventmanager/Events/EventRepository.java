package dev.sashanara.eventmanager.Events;

import dev.sashanara.eventmanager.Events.EventStatusManager.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {

    Optional<EventEntity> findById(Long id);

    @Modifying
    @Query("""
UPDATE EventEntity e
SET e.date = :date,
    e.duration = :duration,
    e.cost = :cost,
    e.maxPlaces = :maxPlaces,
    e.locationId = :locationId,
    e.name = :name
where e.id = :id
""")
    void updateEvent(
            @Param("id") Long id,
            @Param("date") OffsetDateTime date,
            @Param("duration") Integer duration,
            @Param("cost") Integer cost,
            @Param("maxPlaces") Integer maxPlaces,
            @Param("locationId") Long locationId,
            @Param("name") String name
    );

    @Query("""
SELECT e FROM EventEntity e
WHERE (:name IS NULL OR e.name LIKE %:name%)
AND (:minPlaces IS NULL OR e.maxPlaces >= :minPlaces)
AND (:maxPlaces IS NULL OR e.maxPlaces <= :maxPlaces)
AND (CAST(:dateStartAfter as date) IS NULL OR e.date >= :dateStartAfter)
AND (CAST(:dateStartBefore as date) IS NULL OR e.date <= :dateStartBefore)
AND (:minCost IS NULL OR e.cost >= :minCost)
AND (:maxCost IS NULL OR e.cost <= :maxCost)
AND (:minDuration IS NULL OR e.duration >= :minDuration)
AND (:maxDuration IS NULL OR e.duration <= :maxDuration)
AND (:locationId IS NULL OR e.locationId = :locationId)
AND (:status IS NULL OR e.status = :status)
""")
    List<EventEntity> findEventsBySearchRequest(
            @Param("name") String name,
            @Param("minPlaces") Integer minPlaces,
            @Param("maxPlaces") Integer maxPlaces,
            @Param("dateStartAfter") OffsetDateTime dateStartAfter,
            @Param("dateStartBefore") OffsetDateTime dateStartBefore,
            @Param("minCost") Integer minCost,
            @Param("maxCost") Integer maxCost,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("locationId") Long locationId,
            @Param("status") EventStatus status
    );

    @Query("""
SELECT e FROM EventEntity e
WHERE (:ownerId = :ownerId)
""")
    List<EventEntity> getEventsByOwnerId(
            @Param("ownerId") Long ownerId
    );

    @Modifying
    @Query("""
    UPDATE EventEntity e
    SET e.status = :status
    WHERE e.id = :id
""")
    void cancelEventById(
            @Param("id") Long id,
            @Param("status") EventStatus status
    );
}
