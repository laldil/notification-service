package kz.edu.astanait.notification_service.serivce.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.notification_service.dto.PageDto;
import kz.edu.astanait.notification_service.dto.receiver.CreateReceiverRequest;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.mapper.ReceiverMapper;
import kz.edu.astanait.notification_service.repository.ReceiverRepository;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import kz.edu.astanait.notification_service.serivce.ReceiverService;
import kz.edu.astanait.notification_service.serivce.UserService;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ReceiverDto createReceiver(CreateReceiverRequest request) {
        var receiver = ReceiverMapper.MAPPER.mapToEntity(request);
        receiver.setOwner(userService.findById(SecurityUtils.getCurrentId()));

        var savedReceiver = receiverRepository.save(receiver);

        if (!request.getContacts().isEmpty()) {
            var contacts = contactService.createReceiverContact(request.getContacts(), receiver);
            receiver.setContacts(contacts);
        }

        return ReceiverMapper.MAPPER.mapToDto(savedReceiver);
    }

    @Override
    public PageDto<ReceiverDto> getReceivers(int page, int size) {
        var pageRequest = PageRequest.of(page, size, Sort.by("id"));
        var receiversPage = receiverRepository.findByOwnerId(SecurityUtils.getCurrentId(), pageRequest);

        var receiversDto = receiversPage.map(ReceiverMapper.MAPPER::mapToDto);

        return new PageDto<>(receiversDto.getContent(), receiversPage.getTotalElements());
    }

    @Override
    public ReceiverDto addContact(Long receiverId, ReceiverContactDto contactDto) {
        var receiver = receiverRepository.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver with id %d not found".formatted(receiverId)));

        var contact = contactService.createReceiverContact(contactDto, receiver);
        receiver.getContacts().add(contact);

        return ReceiverMapper.MAPPER.mapToDto(receiver);
    }
}
