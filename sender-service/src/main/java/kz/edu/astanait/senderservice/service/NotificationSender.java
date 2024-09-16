package kz.edu.astanait.senderservice.service;


import kz.edu.astanait.senderservice.enums.ContactType;

public interface NotificationSender {

    ContactType getType();

    void sendNotification(String contact, String message);
}
