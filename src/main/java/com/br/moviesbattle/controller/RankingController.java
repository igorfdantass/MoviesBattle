package com.br.moviesbattle.controller;

import com.br.moviesbattle.dto.RankingDto;
import com.br.moviesbattle.service.RankingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/ranking")
public class RankingController {

    private final RankingService rankingService;
    
    @GetMapping
    public Iterable<RankingDto> getTop10ByOrderByTotalPointDesc() {
        return rankingService.getRanking();
    }


}
