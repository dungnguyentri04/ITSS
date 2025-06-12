package com.example.ITSS.controllers;

import com.example.ITSS.config.OurUserInfoDetail;
import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.EvaluationRequestDto;
import com.example.ITSS.dto.responseDto.EvaluationResponseDto;
import com.example.ITSS.service.EvaluationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/evaluations/getAllEvaluation")
    public ResponseEntity<?> getAllEvaluation(){
        List<EvaluationResponseDto> evaluationResponseDtoList = evaluationService.getAllEvaluation();
        ApiResponse<List<EvaluationResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total", evaluationResponseDtoList.size());
        apiResponse.setStatus("success");
        apiResponse.setMessage("All evaluations");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(evaluationResponseDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/evaluation/addEvaluation")
    public ResponseEntity<ApiResponse<EvaluationResponseDto>> addEvaluation(@Valid @RequestBody EvaluationRequestDto evaluationRequestDto, Authentication authentication) {
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        evaluationRequestDto.setReportedName(ourUserDetailService.getUsername());
        EvaluationResponseDto evaluationResponseDto1 = evaluationService.addEvaluation(evaluationRequestDto);
        ApiResponse<EvaluationResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add evaluation successfully");
        response.setData(evaluationResponseDto1);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/evaluation/getEvaluation")
    public ResponseEntity<?> getEvaluation(@RequestParam("evaluationId") Long evaluationId) {
        EvaluationResponseDto evaluationResponseDto = evaluationService.findEvaluationById(evaluationId);
        ApiResponse<EvaluationResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get evaluation successfully");
        response.setData(evaluationResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @PutMapping("/evaluation/updateEvaluation")
//    public ResponseEntity<ApiResponse<EvaluationResponseDto>> updateEvaluation(@RequestParam("evaluationId") Long evaluationId, @RequestBody EvaluationRequestDto evaluationRequestDto) {
//        EvaluationResponseDto evaluationResponseDto = evaluationService.updateEvaluation(evaluationId, evaluationRequestDto);
//        ApiResponse<EvaluationResponseDto> response = new ApiResponse<>();
//        response.setStatus("success");
//        response.setMessage("update evaluation successfully");
//        response.setData(evaluationResponseDto);
//        response.setMetadata(null);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @DeleteMapping("/evaluation/deleteEvaluation")
    public ResponseEntity<ApiResponse<String>> deleteEvaluation(@RequestParam("evaluationId") Long evaluationId){
        String message = evaluationService.deleteEvaluationById(evaluationId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Delete evaluation successfully");
        response.setData(message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/evaluation/getEvaluationByUserId")
    public ResponseEntity<?> getEvaluationByUserId(@RequestParam("userId") Long userId) {
        List<EvaluationResponseDto> evaluationResponseDtoList = evaluationService.findEvaluationByUserId(userId);
        ApiResponse<List<EvaluationResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get evaluation successfully");
        response.setData(evaluationResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/evaluation/getEvaluationByProjectId")
    public ResponseEntity<?> getEvaluationByProjectId(@RequestParam("projectId") Long projectId) {
        List<EvaluationResponseDto> evaluationResponseDtoList = evaluationService.findEvaluationByProjectId(projectId);
        ApiResponse<List<EvaluationResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get evaluation successfully");
        response.setData(evaluationResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
