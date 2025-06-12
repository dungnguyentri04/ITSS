package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {
    private Long id;

    private Long classId;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String userCreatedName;

    private String leaderName;

    private String githubLink;

    private String token;

    private LocalDate createdAt;

    private Long totalTask;

    private Long completedTask;

    private Long progressTask;

    private Long notCompletedTask;

    private Long totalMember;
}
