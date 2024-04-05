package ru.lexp00.platform.tpusers.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lexp00.platform.tpcommon.dtos.users.UserDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpusers.entities.User;
import ru.lexp00.platform.tpusers.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserMapper{
    private final UserRepository userRepository;

    public User toEntity(UserDto userDto) {
        return userRepository.findById(userDto.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with name " + userDto.getUserName()));
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }
}
