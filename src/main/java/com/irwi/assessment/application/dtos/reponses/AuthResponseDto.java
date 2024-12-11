package com.irwi.assessment.application.dtos.reponses;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthResponseDto extends UserResponseDto{
    private String message;
    private String token;
}
