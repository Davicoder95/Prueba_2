package com.irwi.assessment.controller.implement;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.reponses.UserResponseDto;
import com.irwi.assessment.application.dtos.requests.DoctorRequestDto;
import com.irwi.assessment.application.dtos.requests.PatientRequestDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.controller.interfaces.IUserController;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.ports.service.interfaces.IDoctorService;
import com.irwi.assessment.domain.ports.service.interfaces.IPatientService;
import com.irwi.assessment.domain.ports.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Manage user-related requests.")
@CrossOrigin("*")
public class UserControllerImpl implements IUserController {

    private final IUserService userService;
    private final IDoctorService doctorService;
    private final IPatientService patientService;

    @GetMapping("/readAll")
    @Operation(
            summary = "List all users.",
            description = "Provide the token to validate permissions and obtain the list of users.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Override
    public ResponseEntity<List<UserResponseDto>> readAll() {
        List<UserResponseDto> users = userService.readAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get user by ID.",
            description = "Retrieve a user's details by their ID, with proper authentication.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Override
    public ResponseEntity<UserResponseDto> readById(@PathVariable Long id) {
        UserResponseDto user = userService.readById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register/admin")
    @Operation(
            summary = "Create an ADMIN user.",
            description = "Provides the user data and role to create it and validates permissions.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Override
    public ResponseEntity<AuthResponseDto> register(@Validated @RequestBody UserRequestDto requestDto) {
        AuthResponseDto authResponse = userService.register(requestDto, Roles.ADMIN);
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update user details.",
            description = "Update an existing user's details by their ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Override
    public ResponseEntity<UserEntity> update(@Validated @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        UserEntity updatedUser = userService.update(userRequestDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/register/doctor")
    @Operation(
            summary = "Create a DOCTOR user.",
            description = "Create a user with the DOCTOR role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<AuthResponseDto> registerDoctor(@Validated @RequestBody DoctorRequestDto requestDto) {
        AuthResponseDto authResponse = doctorService.registerDoctor(requestDto);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register/patient")
    @Operation(
            summary = "Create a PATIENT user.",
            description = "Create a user with the PATIENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<AuthResponseDto> registerPatient(@Validated @RequestBody PatientRequestDto requestDto) {
        AuthResponseDto authResponse = patientService.registerPatient(requestDto);
        return ResponseEntity.ok(authResponse);
    }
}
