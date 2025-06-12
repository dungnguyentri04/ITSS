package com.example.ITSS.dto.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationRequestDto {
    private String comment;

    private float averageScore;

    private Long reportId;

    private Long evaluatedId;

    private String reportedName;

    @NotNull(message = "projectId is required")
    private Long projectId;

    private Long memberEvaluatedId;

    private Long memberReportedId;

    @NotNull(message = "qualityScore is required")
    private int qualityScore;

    @NotNull(message = "spiritScore is required")
    private int spiritScore;

    @NotNull(message = "communicationScore is required")
    private int communicationScore;

    @NotNull(message = "teamworkScore is required")
    private int teamworkScore;

    @NotNull(message = "strongPoint is required")
    private String strongPoint;

    @NotNull(message = "weakPoint is required")
    private String weakPoint;

    @NotNull(message = "title is required")
    private String title;
}
