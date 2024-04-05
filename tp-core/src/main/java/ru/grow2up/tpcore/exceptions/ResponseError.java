package ru.grow2up.tpcore.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseError {

    @Schema(
            description = "Статус код ответа",
            example = "401"
    )
    private int status;

    @Schema(
            description = "Описание статус кода",
            example = "Incorrect email or password"
    )
    private String message;

    @Schema(
            description = "Дата возникновения статус кода",
            example = "2022-08-25T11:18:55.678+00:00"
    )
    private Date timestamp;

    public ResponseError(int status, String message) {

        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
