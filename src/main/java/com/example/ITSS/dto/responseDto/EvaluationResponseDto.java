package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResponseDto {
    private Long id;

    private String comment;

    private float averageScore;

    private Long reportId;

    private String reportedName;

    private Long evaluatedId;

    private Long projectId;

    private int qualityScore;

    private int spiritScore;

    private int communicationScore;

    private int teamworkScore;

    private String strongPoint;

    private String weakPoint;

    private Date createdAt;

    private Date updatedAt;

    private String evaluatedName;
}
