package com.br.moviesbattle.controller;

import com.br.moviesbattle.dto.UserDto;
import com.br.moviesbattle.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        var user = userService.create(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
