package ru.lexp00.platform.tpchat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grow2up.tpcore.dtos.chat.MessageDto;
import ru.grow2up.tpcore.enums.MessageStatus;
import ru.lexp00.platform.tpchat.entities.Chat;
import ru.lexp00.platform.tpchat.entities.Message;
import ru.lexp00.platform.tpchat.entities.NewMessage;
import ru.lexp00.platform.tpchat.repositories.MessageRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final ChatService chatService;
    private final MessageRepository messageRepository;

    @Transactional
    public ResponseEntity<?> save(NewMessage newMessage) {
        UUID senderId = newMessage.getSenderId();
        Chat chat = chatService.getChatId(senderId, newMessage.getRecipientId(), newMessage.getTenderId()).get();
        Message message = new Message();
        message.setSenderId(senderId);
        message.setRecipientId(newMessage.getRecipientId());
        message.setDescription(newMessage.getDescription());
        message.setStatus(MessageStatus.RECEIVED);
        message.setChatId(chat.getId());
        messageRepository.save(message);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<?> deleteMessage(Long id) {
        messageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .recipientId(message.getRecipientId())
                .tenderId(chatService.findById(message.getChatId()))
                .description(message.getDescription())
                .status(message.getStatus())
                .createAt(message.getCreateAt())
                .build();
    }

    public List<MessageDto> findAllMessageBySenderId(UUID senderId) {

        List<Message> mesList = getMessages(senderId);

        return mesList.stream()
                .map(message -> {
                    message.setStatus(MessageStatus.DELIVERED);
                    return messageRepository.save(message);
                })
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Long getCount(UUID userId) {
        Long countSender = messageRepository.findAllBySenderId(userId).stream()
                .filter(Objects::nonNull)
                .filter(message -> message.getStatus().equals(MessageStatus.RECEIVED))
                .count();
        Long countRecepient = messageRepository.findAllByRecipientId(userId).stream()
                .filter(Objects::nonNull)
                .filter(message -> message.getStatus().equals(MessageStatus.RECEIVED))
                .count();
        return countSender + countRecepient;
    }

    private List<Message> getMessages(UUID senderId) {
        List<Message> mesList = messageRepository.findAllBySenderId(senderId).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Message> mesListRecipient = messageRepository.findAllByRecipientId(senderId).stream()
                .filter(Objects::nonNull)
                .toList();
        mesList.addAll(mesListRecipient);
        return mesList;
    }


}