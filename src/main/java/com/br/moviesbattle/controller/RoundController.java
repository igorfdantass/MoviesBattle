package com.br.moviesbattle.controller;

import com.br.moviesbattle.dto.AnswerRound;
import com.br.moviesbattle.dto.AttemptRound;
import com.br.moviesbattle.dto.RoundDto;
import com.br.moviesbattle.service.InitRoundService;
import com.br.moviesbattle.service.SubmitRoundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Api
@RestController
@AllArgsConstructor
@RequestMapping(path = "/round")
public class RoundController {
    private final InitRoundService initRoundService;

    private final SubmitRoundService submitRoundService;

    @GetMapping("/init/{gameId}")
    public ResponseEntity<RoundDto> initRound(@PathVariable("gameId") Long gameId, HttpServletRequest request) throws ParseException {
        var round = initRoundService.initRound(gameId, request);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @PostMapping("/submit/{gameId}")
    ResponseEntity<AnswerRound> submitRound(@RequestBody AttemptRound attemptRound, @PathVariable("gameId") Long gameId, HttpServletRequest request) throws ParseException {
        return new ResponseEntity<>(submitRoundService.submitAttemp(attemptRound, gameId, request), HttpStatus.OK);
    }

}
