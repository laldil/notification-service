package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.ReceiverEntity;

import java.util.List;

public interface ReceiverContactService {

    ReceiverContactEntity createReceiverContact(ReceiverContactDto contactDto, ReceiverEntity receiver);

    List<ReceiverContactEntity> createReceiverContact(List<ReceiverContactDto> contactDtoList, ReceiverEntity receiver);
}
