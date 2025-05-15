package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("project")
    public ResponseEntity<ApiResponse<ProjectResponseDto>> addProject(@RequestBody ProjectRequestDto projectRequestDto){

        return null;
    }

}
