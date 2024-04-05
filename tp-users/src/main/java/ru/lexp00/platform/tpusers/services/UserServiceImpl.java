package ru.lexp00.platform.tpusers.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lexp00.platform.tpcommon.dtos.users.UserDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpusers.entities.User;
import ru.lexp00.platform.tpusers.mappers.UserMapper;
import ru.lexp00.platform.tpusers.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + id));
        log.info("Return user by id, " + user.getUserId());
        return userMapper.toDto(user);
    }

    @Override
    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("Not found user with id " + userId));
    }

    @Override
    public Optional<User> findByUserId(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUserName(String name) {
        User user = userRepository.findUserByUserName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with name: " + name));
        log.info("Return user by name: " + user.getUserName());
        return user;
    }
}
