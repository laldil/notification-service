package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.PageDto;
import kz.edu.astanait.notification_service.dto.receiver.CreateReceiverRequest;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;

public interface ReceiverService {

    ReceiverDto createReceiver(CreateReceiverRequest request);

    PageDto<ReceiverDto> getReceivers(int page, int size);

    ReceiverDto addContact(Long receiverId, ReceiverContactDto contactDto);
}
