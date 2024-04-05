package ru.lexp00.platform.tpusers.services;

import ru.lexp00.platform.tpcommon.dtos.users.UserDto;
import ru.lexp00.platform.tpusers.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {


    Optional<User> findByUserId(UUID id);

    User findByUserName(String name);
    User findUserById(UUID userId);
    UserDto findById(UUID id);

}
