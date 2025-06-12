package com.example.ITSS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    @NotNull(message = "classId is required")
    private Long classId;

    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "description is required")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "leaderName is required")
    private String leaderName;

    private String userCreatedName;

    private String githubLink;

    private String token;
}
