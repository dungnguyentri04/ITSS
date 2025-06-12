package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.requestDto.ProjectClassMemberRequestDto;
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

    @PostMapping("/projectClassMember/addProjectClassMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> addProjectClassMember(@RequestBody ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.addProjectClassMember(projectClassMemberRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //chuyen thanh string
    @PostMapping("/projectClassMember/deleteProjectClassMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> deleteProjectMember(@RequestBody ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.deleteProjectClassMember(projectClassMemberRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("delete project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @PostMapping("/projectClassMember/updateProjectClassMember")
//    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> updateProjectMember(@RequestBody ProjectClassMemberRequestDto projectClassMemberRequestDto) {
//        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.updateProjectClassMember(projectClassMemberRequestDto);
//        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
//        response.setStatus("success");
//        response.setMessage("update project member successfully");
//        response.setData(projectClassMemberResponseDto);
//        response.setMetadata(null);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    //xem lai
    @GetMapping("/projectClassMember/getProjectMemberUser")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getProjectMemberUser(@RequestParam("userId") Long userId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getProjectMemberUser(userId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //xem lai
    @GetMapping("/projectClassMember/getProjectMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> getProjectMember(@RequestParam("projectMemberId") Long projectMemberId) {
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.findProjectClassMemberById(projectMemberId);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //xem lai
    @GetMapping("/projectClassMember/getProjectMemberProject")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getProjectMemberProject(@RequestParam("projectId") Long projectId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getProjectMemberProject(projectId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectClassMember/getMemberByClassId")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getMemberByClassId(@RequestParam("classId") Long classId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getMemberByClassId(classId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/projectClassMember/getMemberByProjectId")
    public ResponseEntity<ApiResponse<List<ProjectClassMemberResponseDto>>> getMemberByProjectId(@RequestParam("projectId") Long projectId) {
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMemberService.getMemberByProjectId(projectId);
        ApiResponse<List<ProjectClassMemberResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project member successfully");
        response.setData(projectClassMemberResponseDtos);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/projectClassMember/patchProjectClassMember")
    public ResponseEntity<ApiResponse<ProjectClassMemberResponseDto>> updateProjectClassMember(@RequestParam("projectMemberId") Long projectMemberId, @RequestBody ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        System.out.println(projectMemberId);
        ProjectClassMemberResponseDto projectClassMemberResponseDto = projectClassMemberService.updateProjectClassMember(projectMemberId, projectClassMemberRequestDto);
        ApiResponse<ProjectClassMemberResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("patch project member successfully");
        response.setData(projectClassMemberResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
