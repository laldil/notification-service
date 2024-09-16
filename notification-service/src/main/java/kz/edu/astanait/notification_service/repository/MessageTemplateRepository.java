package kz.edu.astanait.notification_service.repository;

import kz.edu.astanait.notification_service.model.MessageTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplateEntity, Long> {

    List<MessageTemplateEntity> findByOwnerId(Long ownerId);
}
