package com.irwi.assessment.controller.implement;

import com.irwi.assessment.application.dtos.reponses.AppointmentResponseDto;
import com.irwi.assessment.application.dtos.requests.AppointmentRequestDto;
import com.irwi.assessment.controller.interfaces.IAppointmentController;
import com.irwi.assessment.domain.entities.Appointment;
import com.irwi.assessment.domain.enums.AppointmentStatus;
import com.irwi.assessment.domain.ports.service.interfaces.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Manage appointment requests.")
@CrossOrigin("*")
public class AppointmentControllerImpl implements IAppointmentController {

    private final IAppointmentService appointmentService;

    @Override
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Create an appointment",
            description = "Creates a new appointment. Requires a valid token."
    )
    public ResponseEntity<Appointment> create(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = appointmentService.create(appointmentRequestDto);
        return ResponseEntity.ok(appointment);
    }

    @Override
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "List all appointments",
            description = "Retrieves a list of all appointments. Requires a valid token."
    )
    public ResponseEntity<List<AppointmentResponseDto>> readAll() {
        List<AppointmentResponseDto> appointments = appointmentService.readAll();
        return ResponseEntity.ok(appointments);
    }

    @Override
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Get an appointment by ID",
            description = "Retrieves an appointment by its ID. Requires a valid token."
    )
    public ResponseEntity<AppointmentResponseDto> readById(@PathVariable Long id) {
        AppointmentResponseDto appointment = appointmentService.readById(id);
        return ResponseEntity.ok(appointment);
    }

    @Override
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Update an appointment",
            description = "Updates an existing appointment by its ID. Requires a valid token."
    )
    public ResponseEntity<Appointment> update(@RequestBody AppointmentRequestDto appointmentRequestDto, @PathVariable Long id) {
        Appointment updatedAppointment = appointmentService.update(appointmentRequestDto, id);
        return ResponseEntity.ok(updatedAppointment);
    }

    @Override
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Delete an appointment",
            description = "Deletes an appointment by its ID. Requires a valid token."
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}/status")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Change the status of an appointment",
            description = "Changes the status of an appointment. Requires a valid token."
    )
    public ResponseEntity<Appointment> changeStatus(@RequestBody AppointmentStatus status, @PathVariable Long id) {
        Appointment updatedAppointment = appointmentService.changeStatus(status, id);
        return ResponseEntity.ok(updatedAppointment);
    }
}
