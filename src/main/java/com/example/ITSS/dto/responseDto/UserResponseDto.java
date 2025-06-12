package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String userName;

    private String role;

    private String email;

    private LocalDate created_at;

    private LocalDate updated_at;

    private String phone;
}
