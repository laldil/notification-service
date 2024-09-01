package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.model.ReceiverEntity;

public interface ReceiverService {

    ReceiverDto createReceiver(ReceiverDto request);
}
