package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.AnswerRound;
import com.br.moviesbattle.model.Game;
import com.br.moviesbattle.model.Movie;
import com.br.moviesbattle.model.Round;
import com.br.moviesbattle.repository.MovieRepository;
import com.br.moviesbattle.security.TokenUtil;
import com.br.moviesbattle.dto.AttemptRound;
import com.br.moviesbattle.repository.GameRepository;
import com.br.moviesbattle.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubmitRoundService {

    private final TokenUtil tokenUtil;

    private final GameRepository gameRepository;

    private final MovieRepository movieRepository;

    private final RoundRepository roundRepository;

    public AnswerRound submitAttemp(AttemptRound attemptRound, Long gameId, HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIdAndIsFinishFalse(userId, gameId);
        var round = roundRepository.findByGameIdAndUserIdAndAnswerFalse(gameId, userId);

        if (gameIsCreated(game)) {
            return AnswerRound.builder().msg("Jogo informado não criado").build();
        }

        if (overTrying(round)) {
            return AnswerRound.builder().msg("Número de tentativas excedido").build();
        }

        return validateAnswer(game, round, attemptRound);
    }

    private Boolean gameIsCreated(Game game){
        return Objects.isNull(game);
    }

    private Boolean overTrying (Round round){
        return round.getRoundNumber() > 3;
    }

    private AnswerRound validateAnswer(Game game, Round round, AttemptRound attemptRound) {
        Integer rightAnswer = getRightAnswer(movieRepository.findById(round.getMovieId1()).get(), movieRepository.findById(round.getMovieId2()).get());
        game.setTotalRound(game.getTotalRound() + 1);
        round.setRoundNumber(round.getRoundNumber() + 1);

        if (isCorrectAnswer(attemptRound, rightAnswer)) {
            calculatePontuation(game);
            round.setAnswer(true);
            roundRepository.save(round);
            return AnswerRound.builder().msg("Resposta correta!").build();
        }
        return AnswerRound.builder().msg("Resposta incorreta!").build();
    }

    private void calculatePontuation(Game game){
        game.setTotalHits(game.getTotalHits() + 1);
        game.setTotalPoint(game.getTotalRound() * ((game.getTotalRound() * 100) / game.getTotalHits()));
    }

    private Boolean isCorrectAnswer(AttemptRound attemptRound, Integer rightAnswer){
        return attemptRound.getOption().equals(rightAnswer);
    }

    private Integer getRightAnswer(Movie movie1, Movie movie2) {
        var amountMovie1 = movie1.getAverageOfVotes() * movie1.getNumberOfVotes();
        var amountMovie2 = movie2.getAverageOfVotes() * movie2.getNumberOfVotes();
        if (amountMovie1 > amountMovie2) {
            return 1;
        } else {
            return 2;
        }
    }
}
