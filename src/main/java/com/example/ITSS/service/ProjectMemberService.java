package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectMemberResponseDto;

import java.util.List;

public interface ProjectMemberService {
    public ProjectMemberResponseDto addProjectMember(ProjectRequestDto projectRequestDto);

    public ProjectMemberResponseDto deleteProjectMember(ProjectRequestDto projectRequestDto);

    public ProjectMemberResponseDto updateProjectMember(ProjectRequestDto projectRequestDto);

    public ProjectMemberResponseDto findProjectMemberById(Long id);

    public List<ProjectMemberResponseDto> getProjectMemberUser(Long userId);

    public List<ProjectMemberResponseDto> getProjectMemberProject(Long projectId);
}
