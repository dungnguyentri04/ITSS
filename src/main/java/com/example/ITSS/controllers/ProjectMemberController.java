package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectMemberResponseDto;
import com.example.ITSS.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProjectMemberController {
    @Autowired
    private ProjectMemberService projectMemberService;

    @PostMapping("/projectMember/addProjectMember")
    public ResponseEntity<ApiResponse<ProjectMemberResponseDto>> addProjectMember(@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectMemberResponseDto projectMemberResponseDto = projectMemberService.addProjectMember(projectRequestDto);
        ApiResponse<ProjectMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add project member successfully");
        response.setData(projectMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //chuyen thanh string
    @PostMapping("/projectMember/deleteProjectMember")
    public ResponseEntity<ApiResponse<ProjectMemberResponseDto>> deleteProjectMember(@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectMemberResponseDto projectMemberResponseDto = projectMemberService.deleteProjectMember(projectRequestDto);
        ApiResponse<ProjectMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("delete project member successfully");
        response.setData(projectMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/projectMember/updateProjectMember")
    public ResponseEntity<ApiResponse<ProjectMemberResponseDto>> updateProjectMember(@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectMemberResponseDto projectMemberResponseDto = projectMemberService.updateProjectMember(projectRequestDto);
        ApiResponse<ProjectMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update project member successfully");
        response.setData(projectMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMemberUser")
    public ResponseEntity<ApiResponse<List<ProjectMemberResponseDto>>> getProjectMemberUser(@RequestParam("userId") Long userId) {
        List<ProjectMemberResponseDto> projectMemberResponseDtos = projectMemberService.getProjectMemberUser(userId);
        ApiResponse<List<ProjectMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMember")
    public ResponseEntity<ApiResponse<ProjectMemberResponseDto>> getProjectMember(@RequestParam("projectMemberId") Long projectMemberId) {
        ProjectMemberResponseDto projectMemberResponseDto = projectMemberService.findProjectMemberById(projectMemberId);
        ApiResponse<ProjectMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMemberProject")
    public ResponseEntity<ApiResponse<List<ProjectMemberResponseDto>>> getProjectMemberProject(@RequestParam("projectId") Long projectId) {
        List<ProjectMemberResponseDto> projectMemberResponseDtos = projectMemberService.getProjectMemberProject(projectId);
        ApiResponse<List<ProjectMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
