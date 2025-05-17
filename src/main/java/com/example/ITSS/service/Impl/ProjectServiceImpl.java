package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.ProjectMember;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.UserRole;
import com.example.ITSS.repositories.ProjectMemberRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.ProjectService;
import com.example.ITSS.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        User createdUser = userRepository.findById(projectRequestDto.getUserCreatedId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        User leaderUser = userRepository.findByUserName(projectRequestDto.getLeaderName());
        if (leaderUser == null) {
            throw new NotFoundException("Leader not found");
        }
        //save project
        Project project = modelMapper.map(projectRequestDto, Project.class);
        project.setUserCreatedName(createdUser.getUserName());
        project.setCreatedAt(LocalDate.now());

        List<ProjectMember> projectMembers = new ArrayList<>();

        //created projectMember
        ProjectMember teacherMember = new ProjectMember();
        teacherMember.setProject(project);
        teacherMember.setRole(UserRole.TEACHER);
        teacherMember.setUser(createdUser);
        teacherMember.setCreatedAt(LocalDate.now());
        projectMembers.add(teacherMember);

        ProjectMember leaderMember = new ProjectMember();
        leaderMember.setProject(project);
        leaderMember.setRole(UserRole.LEADER);
        leaderMember.setUser(leaderUser);
        leaderMember.setCreatedAt(LocalDate.now());
        projectMembers.add(leaderMember);

        //save project
        project.setProjectMembers(projectMembers);

        Project saveProject = projectRepository.save(project);
        projectRepository.save(saveProject);

        //response
        ProjectResponseDto projectResponseDto = modelMapper.map(saveProject, ProjectResponseDto.class);
        return projectResponseDto;
    }

    @Override
    public ProjectResponseDto findProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        return projectResponseDto;
    }

    @Override
    public String deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        projectRepository.delete(project);
        return "Project with ID " + id + " deleted successfully";
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public List<ProjectResponseDto> getAllProject() {
        return null;
    }

    @Override
    public ProjectResponseDto patchProject(Long id, ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public List<ProjectResponseDto> getProjectsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        List<ProjectMember> projectMembers = projectMemberRepository.findByUserId(userId); //
        List<ProjectResponseDto> projectResponseDtos = projectMembers.stream().map(projectMember -> {
            Project project = projectMember.getProject();
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            return projectResponseDto;
        }).toList();
        return projectResponseDtos;
    }
}
