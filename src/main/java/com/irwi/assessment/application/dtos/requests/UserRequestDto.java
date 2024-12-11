package com.irwi.assessment.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    @NotBlank(message = "First name required!")
    private String name;

    @NotBlank(message = "Last name required!")
    private String lastName;

    @NotBlank(message = "Username required!")
    private String username;

    @NotBlank(message = "Email required!")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password required!")
    private String password;
}
