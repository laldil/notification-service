package kz.edu.astanait.notification_service.repository;

import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceiverContactRepository extends JpaRepository<ReceiverContactEntity, Long> {

    @Query(value = """
            select rc from ReceiverContactEntity rc
            join ReceiverEntity r on rc.receiver = r
            where r.owner.id = :ownerId
            and rc.contactType = :type
            """)
    List<ReceiverContactEntity> findByOwnerIdAndContactType(@Param("ownerId") Long ownerId, @Param("type") ContactType type);
}
