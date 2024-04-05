package ru.grow2up.tpcore.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegistrationError {
    @Schema(
            description = "Статус код ответа",
            example = "401"
    )
    private int status;

    @Schema(
            description = "список сообщений с описанием статус кода",
            example = "Incorrect email or password"
    )
    private List<String> message;

    @Schema(
            description = "Дата возникновения статус кода",
            example = "2022-08-25T11:18:55.678+00:00"
    )
    private Date timestamp;

    public RegistrationError(String message) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = new ArrayList<>(Arrays.asList(message));
        this.timestamp = new Date();
    }

    public RegistrationError(List<ObjectError> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = errors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        this.timestamp = new Date();
    }
}
