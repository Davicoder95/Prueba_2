package com.irwi.assessment.application.service;

import com.irwi.assessment.application.dtos.reponses.AppointmentResponseDto;
import com.irwi.assessment.application.dtos.requests.AppointmentRequestDto;
import com.irwi.assessment.application.mappers.AppointmentMapper;
import com.irwi.assessment.domain.entities.Appointment;
import com.irwi.assessment.domain.entities.PatientDetails;
import com.irwi.assessment.domain.entities.DoctorDetails;
import com.irwi.assessment.domain.enums.AppointmentStatus;
import com.irwi.assessment.domain.ports.service.interfaces.IAppointmentService;
import com.irwi.assessment.infrastructure.persistences.AppointmentRepository;
import com.irwi.assessment.infrastructure.persistences.PatientRepository;
import com.irwi.assessment.infrastructure.persistences.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Appointment create(AppointmentRequestDto appointmentRequestDto) {
        if (appointmentRequestDto.getDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date and time cannot be in the past");
        }

        PatientDetails patient = findPatientById(appointmentRequestDto.getPatientId());
        DoctorDetails doctor = findDoctorById(appointmentRequestDto.getDoctorId());

        if (!doctorAvailable(doctor, appointmentRequestDto.getDateTime())) {
            throw new IllegalStateException("Doctor is not available at the requested date and time");
        }

        Appointment appointment = AppointmentMapper.INSTANCE.toEntity(appointmentRequestDto);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus(AppointmentStatus.PENDING);

        System.out.println("Fecha de cita: " + appointment.getAppointmentDate());

        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<AppointmentResponseDto> readAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDto readById(Long id) {
        Appointment appointment = findAppointmentById(id);
        return AppointmentMapper.INSTANCE.toResponseDto(appointment);
    }

    @Override
    public Appointment update(AppointmentRequestDto appointmentRequestDto, Long id) {
        Appointment existingAppointment = findAppointmentById(id);

        if (appointmentRequestDto.getDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date and time cannot be in the past");
        }

        PatientDetails patient = findPatientById(appointmentRequestDto.getPatientId());
        DoctorDetails doctor = findDoctorById(appointmentRequestDto.getDoctorId());

        if (!doctorAvailable(doctor, appointmentRequestDto.getDateTime())) {
            throw new IllegalStateException("Doctor is not available at the requested date and time");
        }

        Appointment updatedAppointment = AppointmentMapper.INSTANCE.toEntity(appointmentRequestDto);
        updatedAppointment.setId(existingAppointment.getId());
        updatedAppointment.setPatient(patient);
        updatedAppointment.setDoctor(doctor);
        updatedAppointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(updatedAppointment);
    }

    @Override
    public Appointment changeStatus(AppointmentStatus status, Long id) {
        Appointment appointment = findAppointmentById(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    private PatientDetails findPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
    }

    private DoctorDetails findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
    }

    private Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + id));
    }

    private boolean doctorAvailable(DoctorDetails doctor, LocalDateTime dateTime) {
        return appointmentRepository.findByDoctor_IdAndAppointmentDate(doctor.getId(), dateTime).isEmpty();
    }
}
