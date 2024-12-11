package com.irwi.assessment.domain.ports.service.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.reponses.UserResponseDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.ports.service.crud.*;

public interface IUserService extends
        ReadAllService<UserResponseDto>,
        ReadByIdService<UserResponseDto, Long>,
        UpdateService<UserRequestDto, UserEntity, Long>{

    AuthResponseDto register(UserRequestDto request, Roles role);
    UserEntity getEntityById(Long id);
}
