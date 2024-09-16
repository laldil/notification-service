package kz.edu.astanait.notification_service.repository;

import kz.edu.astanait.notification_service.model.ReceiverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverRepository extends JpaRepository<ReceiverEntity, Long> {

    Page<ReceiverEntity> findByOwnerId(Long ownerId, Pageable pageable);
}
