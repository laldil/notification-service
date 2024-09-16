package kz.edu.astanait.senderservice.service;


import kz.edu.astanait.senderservice.enums.ContactType;

import java.util.concurrent.CompletableFuture;

public interface NotificationSender {

    ContactType getType();

    CompletableFuture<Boolean> sendNotification(String contact, String message);
}
