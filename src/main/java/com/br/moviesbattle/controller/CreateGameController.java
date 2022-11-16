package com.br.moviesbattle.controller;

import com.br.moviesbattle.dto.GameDto;
import com.br.moviesbattle.service.GameService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Api
@RestController
@AllArgsConstructor
@RequestMapping(path = "/createGame")
public class CreateGameController {
    private final GameService gameService;

    @PostMapping
    public HttpEntity<GameDto> createGame(HttpServletRequest request) throws ParseException {
        var gameDto = gameService.create(request);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }
}
