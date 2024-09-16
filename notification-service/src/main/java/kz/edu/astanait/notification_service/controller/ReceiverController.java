package kz.edu.astanait.notification_service.controller;

import jakarta.validation.Valid;
import kz.edu.astanait.notification_service.dto.PageDto;
import kz.edu.astanait.notification_service.dto.receiver.CreateReceiverRequest;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.serivce.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/receiver")
public class ReceiverController {

    private final ReceiverService receiverService;

    @PostMapping
    public ResponseEntity<ReceiverDto> createReceiver(@Valid @RequestBody CreateReceiverRequest receiverDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receiverService.createReceiver(receiverDto));
    }

    @GetMapping
    public ResponseEntity<PageDto<ReceiverDto>> getReceivers(@RequestParam(required = false, defaultValue = "0") int page,
                                                             @RequestParam(required = false, defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(receiverService.getReceivers(page, size));
    }

    @PostMapping("/{id}/contact")
    public ResponseEntity<ReceiverDto> createContact(@PathVariable Long id, @Valid @RequestBody ReceiverContactDto contactDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receiverService.addContact(id, contactDto));
    }
}
