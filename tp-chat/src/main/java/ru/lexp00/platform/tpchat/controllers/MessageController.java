package ru.lexp00.platform.tpchat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow2up.tpcore.dtos.chat.MessageDto;
import ru.lexp00.platform.tpchat.entities.NewMessage;
import ru.lexp00.platform.tpchat.services.MessageService;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chat")
@Tag(name = "Чат", description = "Методы по работе с чатом и сообщениями")
public class MessageController {
    private final MessageService messageService;
    private final String SEND_MESSAGE = "/send";
    private final String DELETE_MESSAGE_BY_MESSAGE_ID = "/del/{messageId}";
    private final String FIND_ALL_MESSAGE_BY_SEND_ID = "/send/{sendId}";
    private final String GET_COUNT_MESSAGE = "/count/{userId}";

    @Operation(
            summary = "Метод отправки сообщения",
            description = "Метод отправки сообщения"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение отправлено"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Проблема с token: возможно он не актуален"
                    )
            }
    )
    @PutMapping(SEND_MESSAGE)
    public ResponseEntity<?> sendMessage(@RequestBody NewMessage newMessage) {
        return messageService.save(newMessage);
    }

    @Operation(
            summary = "Метод возврата список сообщений по tenderId",
            description = "Метод возврата список сообщений по tenderId"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = MessageDto.class)
                    )
            )
    )

    @GetMapping(FIND_ALL_MESSAGE_BY_SEND_ID)
    public List<MessageDto> findAllMessageBySendId(
            @PathVariable UUID sendId) {
     return messageService.findAllMessageBySenderId(sendId);
    }

    @Operation(
            summary = "Метод удаления сообщения",
            description = "Метод удаления сообщения"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping(DELETE_MESSAGE_BY_MESSAGE_ID)
    public ResponseEntity<?> delete(
            @Parameter(description = "Ид сообщения")
            @PathVariable Long messageId) {

        return messageService.deleteMessage(messageId);
    }


    @Operation(
            summary = "Метод возврата не прочитанных сообщений отправителя по senderId",
            description = "Метод возврата не прочитанных сообщений отправителя по senderId"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = Long.class)
                    )
            )
    )
    @GetMapping(GET_COUNT_MESSAGE)
    public Long getCount(
            @Parameter(description = "Ид пользователя")
            @PathVariable UUID userId) {
        return messageService.getCount(userId);
    }
}
