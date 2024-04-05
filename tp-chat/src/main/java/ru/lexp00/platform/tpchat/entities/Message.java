package ru.lexp00.platform.tpchat.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import ru.grow2up.tpcore.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "messages")
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "recipient_id")
    private UUID recipientId;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "status")
    private MessageStatus status;

}

