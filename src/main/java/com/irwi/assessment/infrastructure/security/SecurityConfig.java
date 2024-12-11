package com.irwi.assessment.infrastructure.security;

import com.irwi.assessment.domain.enums.Roles;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Autowired
    private final JwtFilter jwtFilter;

    @Autowired
    private final AuthenticationProvider authenticationProvider;

    // Define los endpoints públicos que no requieren autenticación
    private static final String[] PUBLIC_ENDPOINTS = {
            "/auth/login",
            "/auth/register/patient",
            "/appointments/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/users/**"
    };

    // Define los endpoints para ADMIN, DOCTOR, y PATIENT
    private static final String[] ADMIN_ENDPOINTS = {
             // Asegúrate de que esta ruta esté correctamente configurada
    };

    private static final String[] DOCTOR_ENDPOINTS = {
            "/doctors/schedule/**"  // Asegúrate de que esta ruta esté correctamente configurada
    };

    private static final String[] PATIENT_ENDPOINTS = {
            "/patients/**"  // Puedes definir rutas específicas para pacientes
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()  // Los endpoints públicos están permitidos
                        .requestMatchers(ADMIN_ENDPOINTS).hasAuthority(Roles.ADMIN.name())  // Permite solo a ADMIN
                        .requestMatchers(DOCTOR_ENDPOINTS).hasAuthority(Roles.DOCTOR.name())  // Permite solo a DOCTOR
                        .requestMatchers(PATIENT_ENDPOINTS).hasAuthority(Roles.PATIENT.name())  // Permite solo a PATIENT
                        .anyRequest().authenticated()  // Cualquier otra ruta requiere autenticación
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Usa JWT y no crea sesiones
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Agrega el filtro JWT antes del filtro de autenticación estándar
                .build();
    }
}
