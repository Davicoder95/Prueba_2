package com.irwi.assessment.application.mappers;

import com.irwi.assessment.application.dtos.reponses.UserResponseDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity (UserRequestDto requestDto);
    UserResponseDto toResponseDto (UserEntity user);

}
