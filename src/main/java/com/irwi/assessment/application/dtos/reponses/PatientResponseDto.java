package com.irwi.assessment.application.dtos.reponses;

import com.irwi.assessment.domain.entities.Appointment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Appointment> appointments;
}
