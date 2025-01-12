package com.fontys.fitquest.controller;

import com.fontys.fitquest.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("notifications")
public class NotificationsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotificationToUser(@RequestBody NotificationMessage message) {
        System.out.println("Sending message to user: " + message.getTo());
        messageService.saveMessage(message);
        messagingTemplate.convertAndSendToUser(message.getTo(), "/queue/messages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/received")
    public ResponseEntity<List<NotificationMessage>> getReceivedMessages(Principal principal) {
        String username = principal.getName();
        System.out.println("Retrieving messages for user: " + username);
        List<NotificationMessage> messages = messageService.getMessagesForUser(username);
        System.out.println("Messages retrieved: " + messages.size());
        return ResponseEntity.ok(messages);
    }
}