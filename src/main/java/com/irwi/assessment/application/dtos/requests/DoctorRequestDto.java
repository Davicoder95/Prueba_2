package com.irwi.assessment.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDto {
    @NotNull
    private UserRequestDto request;

    @NotBlank(message = "Specialty required!")
    private String specialty;

    @NotNull(message = "Availability required!")
    private List<LocalDateTime> availability;
}
