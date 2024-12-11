package com.irwi.assessment.application.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {
    @NotNull(message = "Patient ID required!")
    private Long patientId;

    @NotNull(message = "Doctor ID required!")
    private Long doctorId;

    @NotNull(message = "DateTime required!")
    private LocalDateTime dateTime;

    @NotBlank(message = "Reason required!")
    private String reason;

    private String notes;
}
