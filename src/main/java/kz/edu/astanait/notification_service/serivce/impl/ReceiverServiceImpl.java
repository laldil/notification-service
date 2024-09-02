package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.mapper.ReceiverMapper;
import kz.edu.astanait.notification_service.repository.ReceiverRepository;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import kz.edu.astanait.notification_service.serivce.ReceiverService;
import kz.edu.astanait.notification_service.serivce.UserService;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReceiverServiceImpl implements ReceiverService {

    private final ReceiverRepository receiverRepository;

    private final UserService userService;
    private final ReceiverContactService contactService;

    @Override
    @Transactional
    public ReceiverDto createReceiver(ReceiverDto request) {
        var receiver = ReceiverMapper.MAPPER.mapToEntity(request);
        receiver.setOwner(userService.findById(SecurityUtils.getCurrentId()));

        var savedReceiver = receiverRepository.save(receiver);

        if (!request.getContacts().isEmpty()) {
            var contacts = contactService.createReceiverContact(request.getContacts(), receiver);
            receiver.setContacts(contacts);
        }

        return ReceiverMapper.MAPPER.mapToDto(savedReceiver);
    }
}
