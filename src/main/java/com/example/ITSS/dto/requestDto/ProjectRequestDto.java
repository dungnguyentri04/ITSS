package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaderName;

    private Long userCreatedId;

    private String githubLink;

    private String token;
}
