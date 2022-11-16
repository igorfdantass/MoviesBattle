package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.MovieDto;
import com.br.moviesbattle.dto.RoundDto;
import com.br.moviesbattle.mapper.RoundMapper;
import com.br.moviesbattle.model.Game;
import com.br.moviesbattle.model.Round;
import com.br.moviesbattle.repository.GameRepository;
import com.br.moviesbattle.repository.MovieRepository;
import com.br.moviesbattle.repository.RoundRepository;
import com.br.moviesbattle.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InitRoundService {

    private final TokenUtil tokenUtil;

    private final RoundRepository roundRepository;

    private final GameRepository gameRepository;

    private final MovieRepository movieRepository;

    private final RandomMoviesUtilService randomMoviesUtilService;

    private final RoundMapper roundMapper;

    public RoundDto initRound(Long gameId, HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var round = roundRepository.findByAnswerIsFalseAndGameIdAndUserId(gameId, userId);
        if (roundIsNull(round)) {
            var game = gameRepository.findByUserIdAndIdAndIsFinishFalse(userId, gameId);
            if (gameIsNotNull(game)) {
                var listMovies = randomMoviesUtilService.getMovies(game.getId());
                round = saveRound(userId, game, listMovies);
                game.setTotalRound(round.getRoundNumber());
            }
        }
        return getRoundDto(round);
    }

    private Boolean roundIsNull(Round round){
        return Objects.isNull(round);
    }

    private Boolean gameIsNotNull(Game game){
        return Objects.nonNull(game);
    }

    private Round saveRound(Long userId, Game game, List<MovieDto> listMovies) {
        var round = Round.builder()
                .gameId(game.getId())
                .roundNumber(1L)
                .movieId1(listMovies.get(0).getId())
                .movieId2(listMovies.get(1).getId())
                .answer(false)
                .userId(userId)
                .build();
        return roundRepository.save(round);
    }

    private RoundDto getRoundDto(Round newRound) {
        var roundDto = roundMapper.toDto(newRound);
        roundDto.setMovieName1(movieRepository.findById(roundDto.getMovieId1()).get().getTitle());
        roundDto.setMovieName2(movieRepository.findById(roundDto.getMovieId2()).get().getTitle());
        return roundDto;
    }
}
