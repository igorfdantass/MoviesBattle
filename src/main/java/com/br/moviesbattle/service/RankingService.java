package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.RankingDto;
import com.br.moviesbattle.mapper.RankMapper;
import com.br.moviesbattle.repository.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    private final RankMapper rankMapper;

    public Iterable<RankingDto> getRanking() {
        return rankMapper.toDto(rankingRepository.findTop10ByOrderByTotalPointDesc());
    }

}
