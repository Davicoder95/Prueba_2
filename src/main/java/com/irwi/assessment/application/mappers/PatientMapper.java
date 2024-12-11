package com.irwi.assessment.application.mappers;

import com.irwi.assessment.application.dtos.reponses.PatientResponseDto;
import com.irwi.assessment.application.dtos.requests.PatientRequestDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.domain.entities.PatientDetails;
import com.irwi.assessment.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDetails toEntity(PatientRequestDto requestDto);
    PatientResponseDto toResponseDto(PatientDetails patient);

    default UserEntity mapUserEntity(UserRequestDto requestDto) {
        return UserMapper.INSTANCE.toEntity(requestDto); // Usa el UserMapper para mapear el UserEntity
    }
}
