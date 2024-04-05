package ru.lexp00.platform.tpchat.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chats")
@Builder
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "tender_id")
    private Long tenderId;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "recipient_id")
    private UUID recipientId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;

}
