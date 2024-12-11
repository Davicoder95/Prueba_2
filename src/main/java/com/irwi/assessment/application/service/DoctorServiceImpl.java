package com.irwi.assessment.application.service;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.DoctorRequestDto;
import com.irwi.assessment.domain.entities.DoctorDetails;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.ports.service.interfaces.IDoctorService;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.ports.service.interfaces.IUserService;
import com.irwi.assessment.infrastructure.persistences.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    private final IUserService userService;

    @Override
    public AuthResponseDto registerDoctor(DoctorRequestDto request) {
        AuthResponseDto userResponse = userService.register(request.getRequest(), Roles.DOCTOR);

        UserEntity userEntity = userService.getEntityById(userResponse.getId());

        DoctorDetails doctorDetails = DoctorDetails.builder()
                .user(userEntity)
                .specialty(request.getSpecialty())
                .availability(request.getAvailability())
                .build();

        doctorRepository.save(doctorDetails);

        return userResponse;
    }

}
