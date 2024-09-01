package kz.edu.astanait.notification_service.repository;

import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverContactRepository extends JpaRepository<ReceiverContactEntity, Long> {
}
