package com.example.ITSS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotNull(message = "userName is required")
    private String userName;

    @NotNull(message = "role is required")
    private String role;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "confirmPassword is required")
    private String confirmPassword;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "phone is required")
    private String phone;
}
