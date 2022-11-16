package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.GameDto;
import com.br.moviesbattle.model.Game;
import com.br.moviesbattle.model.Ranking;
import com.br.moviesbattle.security.TokenUtil;
import com.br.moviesbattle.mapper.GameMapper;
import com.br.moviesbattle.repository.GameRepository;
import com.br.moviesbattle.repository.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final RankingRepository rankingRepository;

    private final TokenUtil tokenUtil;

    private final GameMapper gameMapper;

    public GameDto create(HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIsFinishFalse(userId);
        if (Objects.isNull(game)) {
            game = gameRepository.save(Game.builder().userId(userId).totalPoint(0L).totalRound(0L).totalHits(0L).isFinish(false).build());

        }
        return gameMapper.toDto(game);
    }

    public void finishGame(HttpServletRequest request) throws ParseException {
        var userId = tokenUtil.getIdUser(request);
        var game = gameRepository.findByUserIdAndIsFinishFalse(userId);
        game.setIsFinish(true);
        gameRepository.save(game);
        rankingRepository.save(Ranking.builder().totalPoint(game.getTotalPoint()).userId(userId).build());
    }

}
