package ru.grow2up.tpcore.dtos.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDto {

    @Schema(
            description = "Уникальные идентификационный номер Пользователя",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472",
            required = true
    )
    private UUID userId;

    @Schema(
            description = "Фамилия пользователя",
            example = "Петров"
    )
    private String userSurname;

    @Schema(
            description = "Имя пользователя",
            example = "Петр",
            required = true
    )
    private String userName;

    @Schema(
            description = "Электронная почта",
            example = "petr@gmail.com",
            required = true
    )
    private String userEmail;
}
