package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    public ProjectResponseDto findProjectById(Long id);

    public String deleteProjectById(Long id);

    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto);

    public List<ProjectResponseDto> getAllProject();

    public ProjectResponseDto patchProject(Long id, ProjectRequestDto projectRequestDto);

    public List<ProjectResponseDto> getProjectsByUserId(Long userId);
}
