package kz.edu.astanait.senderservice.service;


import kz.edu.astanait.senderservice.dto.NotificationDto;

public interface NotificationService {

    void sendNotification(NotificationDto request);
}
