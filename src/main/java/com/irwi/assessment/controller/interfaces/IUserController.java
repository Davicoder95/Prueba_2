package com.irwi.assessment.controller.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.reponses.UserResponseDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.controller.crud.ReadAllController;
import com.irwi.assessment.controller.crud.ReadByIdController;
import com.irwi.assessment.controller.crud.UpdateController;
import com.irwi.assessment.domain.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public interface IUserController extends
        ReadAllController<UserResponseDto>,
        ReadByIdController<UserResponseDto, Long>,
        UpdateController<UserRequestDto, UserEntity, Long> {

    ResponseEntity<AuthResponseDto> register(UserRequestDto requestDto);
}
