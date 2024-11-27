package com.example.commune.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @Column("message_id")
    private Long messageId;

    @Column("sender_id")
    private Integer senderId;

    @Column("receiver_id")
    private Integer receiverId;

    @Column("content")
    private String content;

    @Column("timestamp")
    private Timestamp timestamp;

    @Column("is_read")
    private Boolean isRead;
}
