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

    private float score;

    private String comment;

    private String createdAt;

    private String updatedAt;

    private Long evaluatorId;

    private Long evaluatedId;

    private Long projectId;
}
