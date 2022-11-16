package com.br.moviesbattle.mapper;

import com.br.moviesbattle.dto.RoundDto;
import com.br.moviesbattle.model.Round;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoundMapper {

    RoundDto toDto(Round round);

    Round toModel(RoundDto dto);
}
