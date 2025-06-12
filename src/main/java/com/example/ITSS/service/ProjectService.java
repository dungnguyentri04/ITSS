package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.GithubProjectRequestDto;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    public String deleteProjectById(Long projectId);

    public ProjectResponseDto findProjectById(Long projectId);

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto projectRequestDto);

    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    public List<ProjectResponseDto> getAllProjects();

    public List<ProjectResponseDto> getProjectsByUserId(Long userId);

    public List<ProjectResponseDto> getProjectsByClassId(Long classId);

    public ProjectResponseDto addGithubData(Long projectId, GithubProjectRequestDto githubProjectRequestDto);
}
