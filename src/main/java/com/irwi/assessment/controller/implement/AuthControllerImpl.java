package com.irwi.assessment.controller.implement;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.AuthRequestDto;
import com.irwi.assessment.controller.interfaces.IAuthController;
import com.irwi.assessment.domain.ports.service.interfaces.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Manage authentication requests.")
@CrossOrigin("*")
public class AuthControllerImpl implements IAuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "Authenticate a user.",
            description = "Authenticates the user using their credentials and generates a token for accessing protected endpoints."
    )
    @Override
    public ResponseEntity<AuthResponseDto> login(@Validated @RequestBody AuthRequestDto requestDto) {
        AuthResponseDto authResponse = authService.login(requestDto);
        return ResponseEntity.ok(authResponse);
    }
}
