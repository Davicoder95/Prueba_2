package com.irwi.assessment.domain.ports.service.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.AuthRequestDto;

public interface IAuthService {
    public AuthResponseDto login (AuthRequestDto requestDto);
}
