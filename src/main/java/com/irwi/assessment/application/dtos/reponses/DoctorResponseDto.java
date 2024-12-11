package com.irwi.assessment.application.dtos.reponses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private List<LocalDateTime> availability;
}
