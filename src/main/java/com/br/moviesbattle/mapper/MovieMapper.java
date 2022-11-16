package com.br.moviesbattle.mapper;

import com.br.moviesbattle.model.Movie;
import com.br.moviesbattle.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    MovieDto toDto(Movie movie);

    Movie toModel(MovieDto dto);

}
