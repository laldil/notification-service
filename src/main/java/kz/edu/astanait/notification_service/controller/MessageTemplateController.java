package kz.edu.astanait.notification_service.controller;

import jakarta.validation.Valid;
import kz.edu.astanait.notification_service.dto.message_template.CreateMessageTemplateRequest;
import kz.edu.astanait.notification_service.dto.message_template.MessageTemplateDto;
import kz.edu.astanait.notification_service.serivce.MessageTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/template")
public class MessageTemplateController {

    private final MessageTemplateService messageTemplateService;

    @PostMapping
    public ResponseEntity<MessageTemplateDto> createTemplate(@Valid @RequestBody CreateMessageTemplateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageTemplateService.createMessageTemplate(request));
    }

    @GetMapping
    public ResponseEntity<List<MessageTemplateDto>> getTemplates() {
        return ResponseEntity.status(HttpStatus.OK).body(messageTemplateService.getMessageTemplates());
    }

    @PatchMapping
    public ResponseEntity<MessageTemplateDto> updateTemplate(@RequestBody MessageTemplateDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(messageTemplateService.updateMessageTemplate(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTemplate(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(messageTemplateService.deleteMessageTemplate(id));
    }
}
