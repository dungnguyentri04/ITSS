package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String userName;

    private String role;

    private String email;

    private String created_at;

    private String updated_at;
}
