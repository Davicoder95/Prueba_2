package com.irwi.assessment.infrastructure.persistences;

import com.irwi.assessment.domain.entities.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientDetails, Long> {
}
