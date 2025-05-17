package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.service.GithubService;
import com.example.ITSS.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private GithubService githubService;

    @PostMapping("/project/addProject")
    public ResponseEntity<ApiResponse<ProjectResponseDto>> addProject(@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.addProject(projectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("project/getAllProject")
    public ResponseEntity<?> getAllProject() {
        List<ProjectResponseDto> allProject = projectService.getAllProject();
        ApiResponse<List<ProjectResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get all project successfully");
        response.setData(allProject);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("project/getProject")
    public ResponseEntity<?> getProject(@RequestParam("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = projectService.findProjectById(projectId);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("project/updateProject")
    public ResponseEntity<?> updateProject(@RequestParam("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.updateProject(projectId, projectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //them link github
    @PatchMapping("project/patchProject")
    public ResponseEntity<?> patchProject(@RequestParam("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.patchProject(projectId, projectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("patch project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //xu ly github
    @GetMapping("project/getGithubData")
    public ResponseEntity<?> getGithubData(@RequestParam("projectId") Long projectId) {
        List<Map<String, Object>> result = githubService.getGithubData(projectId);
        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project successfully");
        response.setData(result);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}