package com.irwi.assessment.controller.interfaces;

import com.irwi.assessment.application.dtos.reponses.AppointmentResponseDto;
import com.irwi.assessment.application.dtos.requests.AppointmentRequestDto;
import com.irwi.assessment.controller.crud.*;
import com.irwi.assessment.domain.entities.Appointment;
import com.irwi.assessment.domain.enums.AppointmentStatus;
import org.springframework.http.ResponseEntity;

public interface IAppointmentController extends
        CreateController<AppointmentRequestDto, Appointment>,
        ReadAllController<AppointmentResponseDto>,
        ReadByIdController<AppointmentResponseDto, Long>,
        UpdateController<AppointmentRequestDto, Appointment, Long>,
        DeleteController<Long> {

    ResponseEntity<Appointment> changeStatus(AppointmentStatus status, Long id);
}
