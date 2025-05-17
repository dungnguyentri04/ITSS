package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationRequestDto {
    private String comment;

    private float score;

    private Long evaluatorId;

    private Long evaluatedId;

    private Long projectId;
}
