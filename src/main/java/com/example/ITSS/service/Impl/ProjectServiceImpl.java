package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.models.Project;
import com.example.ITSS.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Project project = modelMapper.map(projectRequestDto, Project.class);

        return null;
    }

    @Override
    public ProjectResponseDto findProjectById(Long id) {
        return null;
    }

    @Override
    public String deleteProjectById(Long id) {
        return null;
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto) {
        return null;
    }
}
