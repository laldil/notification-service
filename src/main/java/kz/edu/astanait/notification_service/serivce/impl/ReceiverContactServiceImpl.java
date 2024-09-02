package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.mapper.ReceiverContactMapper;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.ReceiverEntity;
import kz.edu.astanait.notification_service.repository.ReceiverContactRepository;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
}
