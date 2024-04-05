package ru.grow2up.tpcore.dtos.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
//@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class RegisterUserDto {

    @Schema(
            description = "Имя пользователя",
            example = "Петр",
            required = true
    )
    @Size(min = 2, message = "Имя пользователя должно состоять минимум из двух знаков")
    private String userName;

    @Schema(
            description = "Новый пароль." +
                    "\"Пароль должен состоить из \n" +
                    "- строка содержит хотя бы одно число;\n" +
                    "- строка содержит хотя бы один спецсимвол;\n" +
                    "- строка содержит хотя бы одну латинскую букву в нижнем регистре;\n" +
                    "- строка содержит хотя бы одну латинскую букву в верхнем регистре;\n" +
                    "- строка состоит не менее, чем из 6 вышеупомянутых символов.",
            example = "1#Qua$",
            required = true
    )
    @Size(min = 6, message = "Пароль должен состоять минимум из шести знаков")
    @NotNull(message = "Пароль не может быть пустым")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}",
    message = "Пароль должен состоить из " +
            "(?=.*[0-9]) - строка содержит хотя бы одно число;\n" +
            "(?=.*[a-z]) - строка содержит хотя бы одну латинскую букву в нижнем регистре;\n" +
            "(?=.*[A-Z]) - строка содержит хотя бы одну латинскую букву в верхнем регистре;\n" +
            "[0-9a-zA-Z]{6,} - строка состоит не менее, чем из 6 вышеупомянутых символов.")
    private String userPassword;

    @Schema(
            description = "Старый пароль",
            example = "password2022",
            required = true
    )
    @Size(min = 6, message = "Пароль должен состоять минимум из шести знаков")
    @NotNull(message = "Пароль не может быть пустым")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}",
            message = "Пароль должен состоить из " +
                    "(?=.*[0-9]) - строка содержит хотя бы одно число;\n" +
                    "(?=.*[a-z]) - строка содержит хотя бы одну латинскую букву в нижнем регистре;\n" +
                    "(?=.*[A-Z]) - строка содержит хотя бы одну латинскую букву в верхнем регистре;\n" +
                    "[0-9a-zA-Z]{6,} - строка состоит не менее, чем из 6 вышеупомянутых символов.")
    private String confirmationPassword;
}
