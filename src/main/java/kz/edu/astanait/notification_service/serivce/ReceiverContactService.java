package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.ReceiverEntity;

public interface ReceiverContactService {

    ReceiverContactEntity createReceiverContact(ReceiverContactDto contactDto, ReceiverEntity receiver);
}
