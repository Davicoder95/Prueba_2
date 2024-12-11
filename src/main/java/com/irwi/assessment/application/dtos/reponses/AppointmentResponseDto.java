package com.irwi.assessment.application.dtos.reponses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDto {
    private Long id;
    private PatientResponseDto patient_id;
    private DoctorResponseDto doctor_id;
    private LocalDateTime appointmentDate;
    private String reason;
    private String notes;
    private String status;
}
