package ru.lexp00.platform.tpchat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grow2up.tpcore.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpchat.entities.Chat;
import ru.lexp00.platform.tpchat.repositories.ChatRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public Optional<Chat> getChatId(UUID senderId, UUID recipientId, Long tenderId) {
        return chatRepository.findBySenderIdAndRecipientIdAndTenderId(senderId, recipientId, tenderId)
                .or(() -> {
                            Chat chat = new Chat();
                            chat.setSenderId(senderId);
                            chat.setRecipientId(recipientId);
                            chat.setTenderId(tenderId);
                            save(chat);
                            return Optional.of(chat);
                        }
                );
    }

    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    public Long findById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found chat with id: " + chatId)).getTenderId();
    }
}
