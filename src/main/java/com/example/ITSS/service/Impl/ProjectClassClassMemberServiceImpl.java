package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ProjectClassMemberResponseDto;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.service.ProjectClassMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectClassClassMemberServiceImpl implements ProjectClassMemberService {
    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public ProjectClassMemberResponseDto addProjectMember(ClassRequestDto classRequestDto) {

        return null;
    }

    @Override
    public ProjectClassMemberResponseDto deleteProjectMember(ClassRequestDto classRequestDto) {
        return null;
    }

    @Override
    public ProjectClassMemberResponseDto updateProjectMember(ClassRequestDto classRequestDto) {
        return null;
    }

    @Override
    public ProjectClassMemberResponseDto findProjectMemberById(Long id) {
        return null;
    }

    @Override
    public List<ProjectClassMemberResponseDto> getProjectMemberUser(Long userId) {
        return null;
    }

    @Override
    public List<ProjectClassMemberResponseDto> getProjectMemberProject(Long projectId) {
        return null;
    }
}
