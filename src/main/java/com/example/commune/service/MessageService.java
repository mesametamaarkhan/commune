package com.example.commune.service;

import com.example.commune.model.Message;
import com.example.commune.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        return messageRepository.sendMessage(message);
    }

    public List<Message> getUnreadMessages(Integer receiverId) {
        return messageRepository.getUnreadMessages(receiverId);
    }

    public List<Message> getConversation(Integer userId1, Integer userId2) {
        return messageRepository.getConversation(userId1, userId2);
    }

    public void markMessagesAsRead(Integer receiverId, Integer senderId) {
        messageRepository.markMessagesAsRead(receiverId, senderId);
    }
}
