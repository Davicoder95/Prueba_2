package com.irwi.assessment.domain.ports.service.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.DoctorRequestDto;

public interface IDoctorService {
    AuthResponseDto registerDoctor(DoctorRequestDto request);
}
