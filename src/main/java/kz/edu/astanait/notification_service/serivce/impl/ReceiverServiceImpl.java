package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.mapper.ReceiverContactMapper;
import kz.edu.astanait.notification_service.mapper.ReceiverMapper;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
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

    @Transactional
    @Override
    public ReceiverDto createReceiver(ReceiverDto request) {
        var receiver = ReceiverMapper.MAPPER.mapToEntity(request);
        receiver.setOwner(userService.findById(SecurityUtils.getCurrentId()));

        var savedReceiver = receiverRepository.save(receiver);
        request.getContacts().forEach(contact -> {
            var contactEntity = contactService.createReceiverContact(contact, savedReceiver);
            receiver.getContacts().add(contactEntity);
        });

        var savedReceiverDto = ReceiverMapper.MAPPER.mapToDto(savedReceiver);
        var contacts = ReceiverContactMapper.MAPPER.mapToDtoList(savedReceiver.getContacts());
        savedReceiverDto.setContacts(contacts);

        return savedReceiverDto;
    }
}
