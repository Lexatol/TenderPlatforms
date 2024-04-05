package ru.lexp00.platform.tptenders.services;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lexp00.platform.tpcommon.dtos.users.UserDto;

import java.util.UUID;

@FeignClient("tp-users")
public interface UserClient {

    @GetMapping("api/v1/users/{id}")
    public UserDto getUserById(@Parameter(description = "Идентификационные номер пользователя") @PathVariable UUID id);
}
