package ru.lexp00.platform.tpusers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lexp00.platform.tpcommon.dtos.users.RegisterUserDto;
import ru.lexp00.platform.tpcommon.exceptions.RegistrationError;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация пользователей", description = "Методы по регистрации пользователей")
public class RegisterController {

    private final static String REGISTER_USER = "/register";

    @PostMapping(REGISTER_USER)
    @Operation(
            summary = "Регистрация пользователя",
            description = "Метод регистрации пользователя в системе"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активационный код для продолжения регистрации выслан на email"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "регистрация пользователя не возможна: такой пользователь существует или не правильны заполнены поля",
                            content = @Content(
                                    schema = @Schema(implementation = RegistrationError.class)
                            )
                    )
            }

    )
    public ResponseEntity<?> registration(
            @RequestBody @Validated RegisterUserDto registerUserDto, BindingResult bindingResult) {
        if (!registerUserDto.getUserPassword().equals(registerUserDto.getConfirmationPassword())) {
            return new ResponseEntity<>(
                    new RegistrationError("Sorry, registration is not possible. The entered passwords do not match"), HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Check your email and follow the link to complete the registration", HttpStatus.OK);
    }
}
