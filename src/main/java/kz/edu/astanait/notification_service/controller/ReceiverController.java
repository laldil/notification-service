package kz.edu.astanait.notification_service.controller;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.serivce.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/receiver")
public class ReceiverController {

    private final ReceiverService receiverService;

    @PostMapping
    public ResponseEntity<ReceiverDto> createReceiver(@RequestBody ReceiverDto receiverDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receiverService.createReceiver(receiverDto));
    }
}
