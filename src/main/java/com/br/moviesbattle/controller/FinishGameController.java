package com.br.moviesbattle.controller;

import com.br.moviesbattle.service.GameService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Api
@RestController
@AllArgsConstructor
@RequestMapping(path = "/finishGame")
public class FinishGameController {
    private final GameService gameService;

    @PostMapping("/{gameId}")
    public HttpEntity<String> finishGame(HttpServletRequest request) throws ParseException {
        gameService.finishGame(request);
        return new ResponseEntity<>("Fim do jogo", HttpStatus.OK);
    }
}
