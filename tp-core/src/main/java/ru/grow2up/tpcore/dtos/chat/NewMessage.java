package ru.grow2up.tpcore.dtos.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMessage {


    @Schema(
            description = "id пользователя, который отправляет сообщение",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472",
            required = true
    )
    @NotNull
    private UUID senderId;

    @Schema(
            description = "id пользователя, которому отправляют сообщение",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472",
            required = true
    )
    @NotNull
    private UUID recipientId;

    @Schema(
            description = "id тендера, к которому будет привязано сообщение",
            example = "1",
            required = true
    )
    @NotNull
    private Long tenderId;

    @Schema(
            description = "Текст сообщения",
            example = "есть уточняющие вопросы по вашему заказу",
            required = true
    )
    @NotNull
    private String description;

}
