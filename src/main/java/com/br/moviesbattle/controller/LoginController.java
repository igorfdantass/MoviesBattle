package com.br.moviesbattle.controller;

import com.br.moviesbattle.dto.UserDto;
import com.br.moviesbattle.model.User;
import com.br.moviesbattle.security.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticateService authenticateService;

    public LoginController(final AuthenticateService service) {
        this.authenticateService = service;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final UserDto dto) {
        final User user = this.authenticateService.authenticate(dto.getUsername(), dto.getPassword());
        final String jwt = this.authenticateService.createJwtToken(user);
        return new ResponseEntity<>(UserDto.builder().token(jwt).name(user.getName()).username(user.getUsername()).build(), HttpStatus.OK);
    }
}
