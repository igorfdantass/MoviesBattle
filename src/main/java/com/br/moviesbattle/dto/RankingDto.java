package com.br.moviesbattle.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankingDto {

    private Long id;

    private Long userId;

    private Long totalPoint;

}
