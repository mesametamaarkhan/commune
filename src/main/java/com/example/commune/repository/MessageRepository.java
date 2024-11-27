package com.example.commune.repository;

import com.example.commune.key.MessageRowMapper;
import com.example.commune.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public Message sendMessage(Message message) {
        String query = "INSERT INTO messages (sender_id, receiver_id, content, timestamp, is_read) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(query, message.getSenderId(), message.getReceiverId(), message.getContent(), message.getTimestamp(), message.getIsRead());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return message;
    }

    public List<Message> getUnreadMessages(Integer receiverId) {
        String query = "SELECT * FROM messages WHERE receiver_id = ? AND is_read = FALSE ORDER BY timestamp ASC";
        return jdbcTemplate.query(query, new Object[]{receiverId}, new MessageRowMapper());
    }

    public List<Message> getConversation(Integer userId1, Integer userId2) {
        String query = "SELECT * FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY timestamp ASC";
        return jdbcTemplate.query(query, new Object[]{userId1, userId2, userId2, userId1}, new MessageRowMapper());
    }

    public void markMessagesAsRead(Integer receiverId, Integer senderId) {
        String query = "UPDATE messages SET is_read = TRUE WHERE receiver_id = ? AND sender_id = ? AND is_read = FALSE";
        jdbcTemplate.update(query, receiverId, senderId);
    }
}
