package com.irwi.assessment.application.service;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.requests.AuthRequestDto;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.exception.InvalidCredentialException;
import com.irwi.assessment.domain.ports.service.interfaces.IAuthService;
import com.irwi.assessment.infrastructure.helpers.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(request.getIdentifier());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new InvalidCredentialException("Invalid credential");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentifier()  , request.getPassword()));

        return AuthResponseDto.builder()
                .message(user.getRole() + " Successfully authentication")
                .token(this.jwtUtil.generateToken(user))
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
