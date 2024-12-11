package com.irwi.assessment.infrastructure.persistences;

import com.irwi.assessment.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor_IdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
}
