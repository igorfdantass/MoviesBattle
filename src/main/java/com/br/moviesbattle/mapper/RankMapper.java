package com.br.moviesbattle.mapper;

import com.br.moviesbattle.dto.RankingDto;
import com.br.moviesbattle.model.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RankMapper {

    Iterable<RankingDto> toDto(Iterable<Ranking> ranking);

}
