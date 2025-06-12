package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.EvaluationRequestDto;
import com.example.ITSS.dto.responseDto.EvaluationResponseDto;

import java.util.List;

public interface EvaluationService {
    public EvaluationResponseDto addEvaluation(EvaluationRequestDto evaluationRequestDto);

    public EvaluationResponseDto updateEvaluation(EvaluationRequestDto evaluationRequestDto);

    public String deleteEvaluationById(Long id);

    public EvaluationResponseDto findEvaluationById(Long id);

    public List<EvaluationResponseDto> findEvaluationByReportId(Long reportId);

    public List<EvaluationResponseDto> findEvaluationByEvaluatedId(Long evaluatedId);

    public List<EvaluationResponseDto> findEvaluationByProjectId(Long projectId);

    public List<EvaluationResponseDto> getAllEvaluation();

    public List<EvaluationResponseDto> findEvaluationByUserId(Long userId);
}
