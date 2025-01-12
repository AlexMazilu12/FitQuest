package com.fontys.fitquest.service;

import com.fontys.fitquest.controller.NotificationMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MessageService {
    private final ConcurrentMap<String, List<NotificationMessage>> userMessages = new ConcurrentHashMap<>();

    public void saveMessage(NotificationMessage message) {
        userMessages.computeIfAbsent(message.getTo(), k -> new ArrayList<>()).add(message);
        System.out.println("Message saved for user: " + message.getTo());
    }

    public List<NotificationMessage> getMessagesForUser(String username) {
        List<NotificationMessage> messages = userMessages.getOrDefault(username, new ArrayList<>());
        System.out.println("Messages retrieved for user: " + username + " - " + messages.size() + " messages");
        return messages;
    }
}