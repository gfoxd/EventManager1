package dev.sashanara.eventmanager.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {

    @Modifying
    @Query("""
DELETE FROM RegistrationEntity r
WHERE (:userId = r.userId AND :eventId = r.eventId)
""")
    void deleteByUserIdAndEventId(
            @Param("userId") Long userId,
            @Param("eventId") Long eventId
    );

    @Query("""
SELECT r FROM RegistrationEntity r
WHERE (:userId = r.userId)
""")
    List<Long> findAllByUserId(
            @Param("userId") Long userId
    );
}
