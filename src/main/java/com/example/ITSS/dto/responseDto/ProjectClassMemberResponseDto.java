package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClassMemberResponseDto {
    private Long id;

    private String nameGithub;

    private Long userId;

    private Long projectId;

    private String role;

    private LocalDate createdAt;

    private String name;
}
