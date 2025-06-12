package com.example.ITSS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequestDto {
    @NotNull(message = "subject is required")
    private String subject;

    @NotNull(message = "description is required")
    private String description;

    private Long userCreatedId;
}
