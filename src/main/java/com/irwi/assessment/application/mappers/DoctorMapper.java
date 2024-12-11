package com.irwi.assessment.application.mappers;

import com.irwi.assessment.application.dtos.reponses.DoctorResponseDto;
import com.irwi.assessment.application.dtos.requests.DoctorRequestDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.domain.entities.DoctorDetails;
import com.irwi.assessment.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDetails toEntity(DoctorRequestDto requestDto);
    DoctorResponseDto toResponseDto(DoctorDetails doctor);

    default UserEntity mapUserEntity(UserRequestDto requestDto) {
        return UserMapper.INSTANCE.toEntity(requestDto); // Usa el UserMapper
    }
}
