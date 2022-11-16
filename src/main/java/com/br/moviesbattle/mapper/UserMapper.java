package com.br.moviesbattle.mapper;

import com.br.moviesbattle.dto.UserDto;
import com.br.moviesbattle.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);
}
