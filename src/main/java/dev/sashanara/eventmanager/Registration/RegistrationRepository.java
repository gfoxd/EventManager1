package dev.sashanara.eventmanager.Registration;

import dev.sashanara.eventmanager.Events.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, EventEntity> {

    @Modifying
    @Query("""
DELETE FROM RegistrationEntity r
WHERE (:userId = r.userId AND  r.eventEntity.id = :eventId)
""")
    void deleteByUserIdAndEventId(
            @Param("userId") Long userId,
            @Param("eventId") Long eventId
    );

    @Query("""
SELECT r FROM RegistrationEntity r
WHERE (:userId = r.userId)
""")
    List<RegistrationEntity> findAllByUserId(
            @Param("userId") Long userId
    );

}
