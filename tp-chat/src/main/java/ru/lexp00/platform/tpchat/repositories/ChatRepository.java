package ru.lexp00.platform.tpchat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lexp00.platform.tpchat.entities.Chat;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findBySenderIdAndRecipientIdAndTenderId(UUID senderId, UUID recipientId, Long tenderId);
}
