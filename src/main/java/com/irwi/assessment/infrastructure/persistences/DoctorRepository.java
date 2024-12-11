package com.irwi.assessment.infrastructure.persistences;

import com.irwi.assessment.domain.entities.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorDetails, Long> {
}
