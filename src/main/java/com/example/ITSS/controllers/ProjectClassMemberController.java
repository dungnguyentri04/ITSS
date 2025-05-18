package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ProjectClassMemberResponseDto;
import com.example.ITSS.service.ProjectClassMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProjectClassMemberController {
    @Autowired
    private ProjectClassMemberService projectClassMemberService;

    @PostMapping("/projectMember/addProjectMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> addProjectMember(@RequestBody ClassRequestDto classRequestDto) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.addProjectMember(classRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //chuyen thanh string
    @PostMapping("/projectMember/deleteProjectMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> deleteProjectMember(@RequestBody ClassRequestDto classRequestDto) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.deleteProjectMember(classRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("delete project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/projectMember/updateProjectMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> updateProjectMember(@RequestBody ClassRequestDto classRequestDto) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.updateProjectMember(classRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMemberUser")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getProjectMemberUser(@RequestParam("userId") Long userId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getProjectMemberUser(userId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> getProjectMember(@RequestParam("projectMemberId") Long projectMemberId) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.findProjectMemberById(projectMemberId);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectMember/getProjectMemberProject")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getProjectMemberProject(@RequestParam("projectId") Long projectId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getProjectMemberProject(projectId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
