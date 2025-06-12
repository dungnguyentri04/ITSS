package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.requestDto.ProjectClassMemberRequestDto;
import com.example.ITSS.dto.responseDto.ProjectClassMemberResponseDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.dto.responseDto.UserResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.UserRole;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.ProjectClassMemberService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectClassMemberServiceImpl implements ProjectClassMemberService {
    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectClassMemberResponseDto addProjectClassMember(ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        Long projectId = projectClassMemberRequestDto.getProjectId();
        String username = projectClassMemberRequestDto.getUsername();
        ProjectClassMember projectClassMember = new ProjectClassMember();
        Class aClass = new Class();
        User user = userRepository.findByUserName(username);
        if (user == null) throw new NotFoundException("User not found");
        //them vao project
        if (projectId != null) {
            Project project = projectRepository.findById(projectId).orElseThrow(
                    () -> new NotFoundException("Project not found")
            );
            Long classId = project.getClassroom().getId();
            aClass = classRepository.findById(classId).orElseThrow(
                    () -> new NotFoundException("Class not found")
            );
            projectClassMember = projectClassMemberRepository.findByUserAndClassroom(user, aClass);
            if (projectClassMember.getProjectId() != null) {
                throw new IllegalArgumentException("User already in another project");
            }
            projectClassMember.setProjectId(projectId);
            projectClassMember.setRole(UserRole.MEMBER);
        }
        //them vao class
        else {
            aClass = classRepository.findById(projectClassMemberRequestDto.getClassId()).orElseThrow(
                    () -> new NotFoundException("Class not found")
            );
            ProjectClassMember existingMember = projectClassMemberRepository.findByUserAndClassroom(user, aClass);
            if (existingMember != null) {
                throw new IllegalArgumentException("User already in class");
            }
            projectClassMember.setClassroom(aClass);
            projectClassMember.setUser(user);
            projectClassMember.setUsername(username);
            projectClassMember.setCreatedAt(LocalDate.now());
            projectClassMember.setRole(UserRole.STUDENT);
        }
        projectClassMember = projectClassMemberRepository.save(projectClassMember);

        //response
        ProjectClassMemberResponseDto projectClassMemberResponseDto = modelMapper.map(projectClassMember, ProjectClassMemberResponseDto.class);
        projectClassMemberResponseDto.setRole(projectClassMember.getRole().toString());
        projectClassMemberResponseDto.setClassId(aClass.getId());
        return projectClassMemberResponseDto;
    }

    @Override
    public ProjectClassMemberResponseDto deleteProjectClassMember(ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        return null;
    }

    @Override
    public ProjectClassMemberResponseDto updateProjectClassMember(Long id, ProjectClassMemberRequestDto projectClassMemberRequestDto) {
        ProjectClassMember projectClassMember = projectClassMemberRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ProjectClassMember not found")
        );
        if (projectClassMemberRequestDto.getNameGithub() != null) {
            projectClassMember.setNameGithub(projectClassMemberRequestDto.getNameGithub());
        }
        projectClassMemberRepository.save(projectClassMember);
        ProjectClassMemberResponseDto projectClassMemberResponseDto = modelMapper.map(projectClassMember, ProjectClassMemberResponseDto.class);
        projectClassMemberResponseDto.setUserId(projectClassMember.getUser().getId());
        return projectClassMemberResponseDto;
    }

    @Override
    public ProjectClassMemberResponseDto findProjectClassMemberById(Long id) {

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

    @Override
    public List<ProjectClassMemberResponseDto> getMemberByClassId(Long classId) {
        Class aClass = classRepository.findById(classId).orElseThrow(
                () -> new NotFoundException("Class not found")
        );
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByClassroomId(classId);
        System.out.println(projectClassMembers);
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMembers.stream().map(projectClassMember -> {
            ProjectClassMemberResponseDto projectClassMemberResponseDto = modelMapper.map(projectClassMember, ProjectClassMemberResponseDto.class);
            projectClassMemberResponseDto.setUserId(projectClassMember.getUser().getId());
            return projectClassMemberResponseDto;
        }).toList();
        return projectClassMemberResponseDtos;
    }

    @Override
    public List<ProjectClassMemberResponseDto> getMemberByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByProjectId(projectId);
        List<ProjectClassMemberResponseDto> projectClassMemberResponseDtos = projectClassMembers.stream().map(projectClassMember -> {
            ProjectClassMemberResponseDto projectClassMemberResponseDto = modelMapper.map(projectClassMember, ProjectClassMemberResponseDto.class);
            projectClassMemberResponseDto.setUserId(projectClassMember.getUser().getId());
            return projectClassMemberResponseDto;
        }).toList();
        return projectClassMemberResponseDtos;
    }
}
