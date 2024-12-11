package com.irwi.assessment.application.service;

import com.irwi.assessment.application.dtos.reponses.AuthResponseDto;
import com.irwi.assessment.application.dtos.reponses.UserResponseDto;
import com.irwi.assessment.application.dtos.requests.UserRequestDto;
import com.irwi.assessment.application.mappers.UserMapper;
import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.domain.enums.Roles;
import com.irwi.assessment.domain.exception.InvalidCredentialException;
import com.irwi.assessment.domain.ports.service.interfaces.IUserService;
import com.irwi.assessment.infrastructure.persistences.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> readAll() {
        List<UserEntity> userList = userRepository.findAll();

        return userList.stream()
                .map(UserMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto readById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserMapper.INSTANCE.toResponseDto(user);
    }

    public UserEntity update(UserRequestDto userRequestDto, Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());

        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public AuthResponseDto register(UserRequestDto request, Roles role) {
        UserEntity userDB = userRepository.findByUsernameOrEmail(request.getUsername(),request.getEmail());

        if(userDB != null){
            throw new InvalidCredentialException("Username register");
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(role)
                .enabled(true)
                .build();


        user = this.userRepository.save(user);

        return AuthResponseDto.builder()
                .message(user.getRole() + " successfully authenticated")
                //.token(this.jwtUtil.generateToken(user))
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public UserEntity getEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }

}
