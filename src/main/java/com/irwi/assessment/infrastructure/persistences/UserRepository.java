package com.irwi.assessment.infrastructure.persistences;

import com.irwi.assessment.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameOrEmail (String username, String email);
}
