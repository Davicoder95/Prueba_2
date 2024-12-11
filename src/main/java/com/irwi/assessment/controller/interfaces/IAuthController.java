package com.irwi.assessment.controller.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.AuthRequestDto;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<AuthResponseDto> login (AuthRequestDto requestDto);
}
