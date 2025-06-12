package com.example.ITSS.controllers;

import com.example.ITSS.config.OurUserInfoDetail;
import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.MemberStatsDTO;
import com.example.ITSS.dto.requestDto.GithubProjectRequestDto;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.GitContributionResponseDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.service.GithubService;
import com.example.ITSS.service.MemberStatService;
import com.example.ITSS.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private MemberStatService memberStatService;

    @PostMapping("/project/addProject")
    public ResponseEntity<ApiResponse<ProjectResponseDto>> addProject(@RequestBody ProjectRequestDto projectRequestDto, Authentication authentication) {
        System.out.println(projectRequestDto);
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        projectRequestDto.setUserCreatedName(ourUserDetailService.getUsername());
        System.out.println(ourUserDetailService.getUsername());
        ProjectResponseDto projectResponseDto1 = projectService.addProject(projectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add project successfully");
        response.setData(projectResponseDto1);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/project/getAllProjects")
    public ResponseEntity<?> getAllProjects() {
        List<ProjectResponseDto> allProjects = projectService.getAllProjects();
        ApiResponse<List<ProjectResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get all projects successfully");
        response.setData(allProjects);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.    OK).body(response);
    }

    @GetMapping("/project/getProject")
    public ResponseEntity<?> getProject(@RequestParam("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = projectService.findProjectById(projectId);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/project/updateProject/")
    public ResponseEntity<?> updateProject(@RequestParam("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.updateProject(projectId, projectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update project successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/project/deleteProject/{projectId}")
    public ResponseEntity<ApiResponse<String>> deleteProject(@PathVariable Long projectId){
        String message = projectService.deleteProjectById(projectId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Delete project successfully");
        response.setData(message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/project/getProjectByClassId")
    public ResponseEntity<?> getProjectByClassId(@RequestParam("classId") Long classId) {
        List<ProjectResponseDto> projectResponseDtoList = projectService.getProjectsByClassId(classId);
        ApiResponse<List<ProjectResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get project successfully");
        response.setData(projectResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/project/getGithubData")
    public ResponseEntity<?> getGithubData(@RequestParam("projectId") Long projectId) {
        List<GitContributionResponseDto> githubData = githubService.getGithubData(projectId);
        ApiResponse<List<GitContributionResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get github data successfully");
        response.setData(githubData);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/project/addGithubData")
    public ResponseEntity<?> addGithubData(@RequestParam("projectId") Long projectId, @RequestBody GithubProjectRequestDto githubProjectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.addGithubData(projectId, githubProjectRequestDto);
        ApiResponse<ProjectResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add github data successfully");
        response.setData(projectResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/project/getMemberStats")
    public ResponseEntity<?> getMemberStats(@RequestParam("projectId") Long projectId) {
        List<MemberStatsDTO> memberStatsDTOList = memberStatService.getMemberStats(projectId);
        ApiResponse<List<MemberStatsDTO>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get member stats successfully");
        response.setData(memberStatsDTOList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/project/updateGithubData")
    public ResponseEntity<?> updateGithubData(@RequestParam("projectId") Long projectId) {
        String message = githubService.updateGithubData(projectId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update github data successfully");
        response.setData(message);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
