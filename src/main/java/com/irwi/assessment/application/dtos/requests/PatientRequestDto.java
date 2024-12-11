package com.irwi.assessment.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDto {
    @NotNull
    private UserRequestDto request;

    private String phoneNumber;
}
