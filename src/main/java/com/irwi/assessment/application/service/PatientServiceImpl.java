package com.irwi.assessment.application.service;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.PatientRequestDto;
import com.irwi.assessment.domain.entities.PatientDetails;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.ports.service.interfaces.IPatientService;
import com.irwi.assessment.domain.ports.service.interfaces.IUserService;
import com.irwi.assessment.infrastructure.persistences.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    private final IUserService userService;

    @Override
    public AuthResponseDto registerPatient(PatientRequestDto request) {
        AuthResponseDto userResponse = userService.register(request.getRequest(), Roles.PATIENT);

        UserEntity userEntity = userService.getEntityById(userResponse.getId());

        PatientDetails patientDetails = PatientDetails.builder()
                .user(userEntity)
                .phoneNumber(request.getPhoneNumber())
                .build();

        patientRepository.save(patientDetails);

        return userResponse;
    }
}
