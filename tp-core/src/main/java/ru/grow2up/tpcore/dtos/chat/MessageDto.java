package ru.grow2up.tpcore.dtos.chat;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.grow2up.tpcore.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @Schema(
            description = "id сообщения",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "id отправителя",
            example = ""
    )
    private UUID senderId;

    @Schema(
            description = "id получателя",
            example = ""
    )
    private UUID recipientId;

    private Long tenderId;

    @Schema(
            description = "Текст сообщения",
            example = "есть уточняющие вопросы по вашему заказу"
    )
    private String description;

    @Schema(
            description = "Дата создания сообщения",
            example = "10 12 2010"
    )
    private LocalDateTime createAt;


    @Schema(
            description = "Статус тендера: RECEIVED (присваивается при отправке сообщения), DELIVERED (присваивается при вычитывании сообщения)"
    )
    private MessageStatus status;



}