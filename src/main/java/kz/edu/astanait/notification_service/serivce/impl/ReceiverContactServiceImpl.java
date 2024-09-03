package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.mapper.ReceiverContactMapper;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.ReceiverEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.repository.ReceiverContactRepository;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReceiverContactServiceImpl implements ReceiverContactService {

    private final ReceiverContactRepository repository;

    @Override
    public ReceiverContactEntity createReceiverContact(ReceiverContactDto contactDto, ReceiverEntity receiver) {
        var contact = ReceiverContactMapper.MAPPER.mapToEntity(contactDto);
        contact.setReceiver(receiver);
        return repository.save(contact);
    }

    @Override
    public List<ReceiverContactEntity> createReceiverContact(List<ReceiverContactDto> contactDtoList, ReceiverEntity receiver) {
        var contactList = contactDtoList.stream()
                .map(contactDto -> {
                    var contact = ReceiverContactMapper.MAPPER.mapToEntity(contactDto);
                    contact.setReceiver(receiver);
                    return contact;
                })
                .toList();

        return repository.saveAll(contactList);
    }

    @Override
    public List<ReceiverContactEntity> getReceiverContacts(Long ownerId, ContactType contactType) {
        var contacts = repository.findByOwnerIdAndContactType(ownerId, contactType);
        return contacts.isEmpty() ? Collections.emptyList() : contacts;
    }
}
