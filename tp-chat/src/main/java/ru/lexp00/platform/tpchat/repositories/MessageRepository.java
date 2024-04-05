package ru.lexp00.platform.tpchat.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lexp00.platform.tpchat.entities.Message;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySenderId(UUID senderId);

    List<Message> findAllByRecipientId(UUID recipientId);
}
