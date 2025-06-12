package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponseDto {
    private Long id;

    private String subject;

    private String description;

    private String userCreatedName;

    private LocalDate createdAt;

    private String codeClass;

    private Long totalMember;
}
