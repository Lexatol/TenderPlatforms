package ru.grow2up.tpcore.dtos.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// обертка над пользователем, для json-на аутентификации
@Data
public class JwtRequest {

    @Schema(
            description = "Электронная почта",
            example = "petr@gmail.com",
            required = true
    )
    private String email;

    @Schema(
            description = "Пароль",
            example = "password2022",
            required = true
    )
    private String password;
}
