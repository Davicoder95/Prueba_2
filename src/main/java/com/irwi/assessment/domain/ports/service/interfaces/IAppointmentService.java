package com.irwi.assessment.domain.ports.service.interfaces;

import com.irwi.assessment.application.dtos.reponses.AppointmentResponseDto;
import com.irwi.assessment.application.dtos.requests.AppointmentRequestDto;
import com.irwi.assessment.domain.entities.Appointment;
import com.irwi.assessment.domain.enums.AppointmentStatus;
import com.irwi.assessment.domain.ports.service.crud.*;

public interface IAppointmentService extends
        CreateService<AppointmentRequestDto, Appointment>,
        ReadAllService<AppointmentResponseDto>,
        ReadByIdService<AppointmentResponseDto, Long>,
        UpdateService<AppointmentRequestDto, Appointment, Long>,
        DeleteService<Long> {

    Appointment changeStatus(AppointmentStatus status, Long id);
}
