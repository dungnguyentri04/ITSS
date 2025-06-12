package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.EvaluationRequestDto;
import com.example.ITSS.dto.responseDto.EvaluationResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Evaluation;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.User;
import com.example.ITSS.repositories.EvaluationRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.EvaluationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public EvaluationResponseDto addEvaluation(EvaluationRequestDto evaluationRequestDto) {
        User reporter = userRepository.findByUserName(evaluationRequestDto.getReportedName());
        if (reporter == null) {
            throw new NotFoundException("Reporter not found");
        }
        User evaluated = userRepository.findById(evaluationRequestDto.getEvaluatedId()).orElseThrow(
                () -> new NotFoundException("Evaluated not found"));
        Project project = projectRepository.findById(evaluationRequestDto.getProjectId()).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        Evaluation evaluation = modelMapper.map(evaluationRequestDto, Evaluation.class);
        evaluation.setProject(project);
        evaluation.setReporter(reporter);
        evaluation.setEvaluated(evaluated);
        evaluation.setCreatedAt(LocalDate.now());
        evaluation = evaluationRepository.save(evaluation);
        EvaluationResponseDto responseDto = modelMapper.map(evaluation, EvaluationResponseDto.class);
        return responseDto;
    }

    @Override
    public EvaluationResponseDto updateEvaluation(EvaluationRequestDto evaluationRequestDto) {
        return null;
    }

    @Override
    public String deleteEvaluationById(Long id) {
        return null;
    }

    @Override
    public EvaluationResponseDto findEvaluationById(Long id) {
        return null;
    }

    @Override
    public List<EvaluationResponseDto> findEvaluationByReportId(Long reportId) {
        return null;
    }

    @Override
    public List<EvaluationResponseDto> findEvaluationByEvaluatedId(Long evaluatedId) {
        return null;
    }

    @Override
    public List<EvaluationResponseDto> findEvaluationByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        List<Evaluation> evaluations = evaluationRepository.findByProjectId(projectId);
        List<EvaluationResponseDto> evaluationResponseDtos = evaluations.stream().map(evaluation -> {
            EvaluationResponseDto evaluationResponseDto = modelMapper.map(evaluation, EvaluationResponseDto.class);
            evaluationResponseDto.setEvaluatedName(evaluation.getEvaluated().getUserName());
            return evaluationResponseDto;
        }).toList();
        return evaluationResponseDtos;
    }

    @Override
    public List<EvaluationResponseDto> getAllEvaluation() {
        return null;
    }

    @Override
    public List<EvaluationResponseDto> findEvaluationByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        List<Evaluation> evaluations = evaluationRepository.findByEvaluatedId(userId);
        List<EvaluationResponseDto> evaluationResponseDtos = evaluations.stream().map(evaluation -> {
            EvaluationResponseDto evaluationResponseDto = modelMapper.map(evaluation, EvaluationResponseDto.class);
            evaluationResponseDto.setEvaluatedName(evaluation.getEvaluated().getUserName());
            return evaluationResponseDto;
        }).toList();
        return evaluationResponseDtos;
    }
}
