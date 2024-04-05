package ru.lexp00.platform.tpusers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lexp00.platform.tpcommon.dtos.users.UserDto;
import ru.lexp00.platform.tpusers.services.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Tag(name = "Пользователи", description = "Методы для работы с пользователями")
public class UserController {
    private final UserService userService;

    private final String FIND_USER_BY_ID = "/{id}";

    @GetMapping(FIND_USER_BY_ID)
    @Operation(
            summary = "Поиск пользователя по id",
            description = "Поиск пользователя по id"
    )
    @ApiResponse(
            content = @Content(
                    schema = @Schema(implementation = UserDto.class)
            )
    )
    public UserDto getUserById(
            @Parameter(description = "Идентификационные номер пользователя")
            @PathVariable UUID id) {
        return userService.findById(id);
    }



}
