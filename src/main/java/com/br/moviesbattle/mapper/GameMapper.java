package com.br.moviesbattle.mapper;

import com.br.moviesbattle.dto.GameDto;
import com.br.moviesbattle.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

    GameDto toDto(Game game);

    Game toModel(GameDto dto);

}
