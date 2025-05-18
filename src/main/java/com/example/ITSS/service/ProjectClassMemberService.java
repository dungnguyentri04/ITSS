package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ProjectClassMemberResponseDto;

import java.util.List;

public interface ProjectClassMemberService {
    public ProjectClassMemberResponseDto addProjectMember(ClassRequestDto classRequestDto);

    public ProjectClassMemberResponseDto deleteProjectMember(ClassRequestDto classRequestDto);

    public ProjectClassMemberResponseDto updateProjectMember(ClassRequestDto classRequestDto);

    public ProjectClassMemberResponseDto findProjectMemberById(Long id);

    public List<ProjectClassMemberResponseDto> getProjectMemberUser(Long userId);

    public List<ProjectClassMemberResponseDto> getProjectMemberProject(Long projectId);
}
