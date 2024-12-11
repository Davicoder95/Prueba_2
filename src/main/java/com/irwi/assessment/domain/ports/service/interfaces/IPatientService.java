package com.irwi.assessment.domain.ports.service.interfaces;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.PatientRequestDto;

public interface IPatientService {
    AuthResponseDto registerPatient(PatientRequestDto request);
}
