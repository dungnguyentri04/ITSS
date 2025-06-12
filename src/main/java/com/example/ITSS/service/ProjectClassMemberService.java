package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.requestDto.ProjectClassMemberRequestDto;
import com.example.ITSS.dto.responseDto.ProjectClassMemberResponseDto;

import java.util.List;

public interface ProjectClassMemberService {
    public ProjectClassMemberResponseDto addProjectClassMember(ProjectClassMemberRequestDto projectClassMemberRequestDto);

    public ProjectClassMemberResponseDto deleteProjectClassMember(ProjectClassMemberRequestDto projectClassMemberRequestDto);

    public ProjectClassMemberResponseDto updateProjectClassMember(Long id, ProjectClassMemberRequestDto projectClassMemberRequestDto);

    public ProjectClassMemberResponseDto findProjectClassMemberById(Long id);

    public List<ProjectClassMemberResponseDto> getProjectMemberUser(Long userId);

    public List<ProjectClassMemberResponseDto> getProjectMemberProject(Long projectId);

    public List<ProjectClassMemberResponseDto> getMemberByClassId(Long classId);

    public List<ProjectClassMemberResponseDto> getMemberByProjectId(Long projectId);
}
