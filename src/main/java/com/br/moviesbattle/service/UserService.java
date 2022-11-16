package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.UserDto;
import com.br.moviesbattle.mapper.UserMapper;
import com.br.moviesbattle.model.User;
import com.br.moviesbattle.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto create(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        var user = userRepository.save(userMapper.toModel(userDto));
        return userMapper.toDto(user);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

