package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectMemberResponseDto;
import com.example.ITSS.models.Project;
import com.example.ITSS.repositories.ProjectMemberRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectMemberResponseDto addProjectMember(ProjectRequestDto projectRequestDto) {

        return null;
    }

    @Override
    public ProjectMemberResponseDto deleteProjectMember(ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public ProjectMemberResponseDto updateProjectMember(ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public ProjectMemberResponseDto findProjectMemberById(Long id) {
        return null;
    }

    @Override
    public List<ProjectMemberResponseDto> getProjectMemberUser(Long userId) {
        return null;
    }

    @Override
    public List<ProjectMemberResponseDto> getProjectMemberProject(Long projectId) {
        return null;
    }
}
