package com.irwi.assessment.application.mappers;

import com.irwi.assessment.application.dtos.reponses.AppointmentResponseDto;
import com.irwi.assessment.application.dtos.requests.AppointmentRequestDto;
import com.irwi.assessment.domain.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "dateTime", target = "appointmentDate")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "doctorId", target = "doctor.id")
    Appointment toEntity(AppointmentRequestDto requestDto);
    AppointmentResponseDto toResponseDto(Appointment appointment);
}
