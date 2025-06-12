package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.GithubProjectRequestDto;
import com.example.ITSS.dto.requestDto.ProjectRequestDto;
import com.example.ITSS.dto.responseDto.ProjectResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.exception.NullInformationException;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.User;
import com.example.ITSS.repositories.*;
import com.example.ITSS.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ITSS.models.enums.*;

import javax.swing.event.ListDataEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;


    @Override
    public String deleteProjectById(Long projectId) {
        return null;
    }

    @Override
    public ProjectResponseDto findProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        projectResponseDto.setClassId(project.getClassroom().getId());
        Long totalTask = taskRepository.countByProjectId(projectId);
        Long completedTask = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.FINISHED);
        Long uncompletedTask = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.NOT_FINISHED);
        Long progressTask = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.IN_PROGRESS);
        projectResponseDto.setProgressTask(progressTask);
        projectResponseDto.setNotCompletedTask(uncompletedTask);
        projectResponseDto.setTotalTask(totalTask);
        projectResponseDto.setCompletedTask(completedTask);
        return projectResponseDto;
    }

    @Override
    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        User leader = userRepository.findByUserName(projectRequestDto.getLeaderName());
        if (leader == null) {
            throw new NotFoundException("Leader not found");
        }
        User userCreated = userRepository.findByUserName(projectRequestDto.getUserCreatedName());
        if (userCreated == null) {
            throw new NotFoundException("User created not found");
        }
        Long classId = projectRequestDto.getClassId();
        Class aClass = classRepository.findById(classId).orElseThrow(
                () -> new NotFoundException("Class not found")
        );
        //save project
        Project project = modelMapper.map(projectRequestDto, Project.class);
        project.setClassroom(aClass);
        project.setCreatedAt(LocalDate.now());

        ProjectClassMember projectClassMember = projectClassMemberRepository.findByUserAndClassroom(leader, aClass);
        if (projectClassMember == null) {
            throw new NotFoundException("Leader not found in class");
        }

        //save projectClassMember ( leader )
        projectClassMember.setRole(UserRole.valueOf("LEADER"));
        project = projectRepository.save(project);
        projectClassMember.setProjectId(project.getId());
        projectClassMemberRepository.save(projectClassMember);

        //response
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        projectResponseDto.setClassId(classId);
        return projectResponseDto;
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResponseDto> projectResponseDtos = projects.stream().map(project -> {
            Class aClass = project.getClassroom();
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDto.setClassId(aClass.getId());
            projectResponseDto.setTotalMember(projectClassMemberRepository.countByProjectId(project.getId()));
            return projectResponseDto;
        }).toList();
        return projectResponseDtos;
    }

    @Override
    public List<ProjectResponseDto> getProjectsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        UserRole role = user.getRole();
        if (role.equals(UserRole.TEACHER)) {
            String userName = user.getUserName();
            List<Project> projects = projectRepository.findAll();
            List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
            for (Project project : projects) {
                if (userName.equals(project.getUserCreatedName())) {
                    Class aClass = project.getClassroom();
                    ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
                    projectResponseDto.setClassId(aClass.getId());
                    projectResponseDtos.add(projectResponseDto);
                }
            }
            return projectResponseDtos;
        }

        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByUserId(userId);
        List<ProjectResponseDto> projectResponseDtos = projectClassMembers.stream().map(projectClassMember -> {
            Long projectId = projectClassMember.getProjectId();
            Project project = projectRepository.findById(projectId).orElseThrow(
                    () -> new NotFoundException("Project not found")
            );
            Class aClass = project.getClassroom();
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDto.setClassId(aClass.getId());
            return projectResponseDto;
        }).toList();
        return projectResponseDtos;
    }

    @Override
    public List<ProjectResponseDto> getProjectsByClassId(Long classId) {
        Class aClass = classRepository.findById(classId).orElseThrow(
                () -> new NotFoundException("Class not found")
        );
        List<Project> projects = projectRepository.findByClassroomId(classId);
        List<ProjectResponseDto> projectResponseDtos = projects.stream().map(project -> {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDto.setTotalTask(taskRepository.countByProjectId(project.getId()));
            projectResponseDto.setCompletedTask(taskRepository.countByProjectIdAndStatus(project.getId(), TaskStatus.FINISHED));
            projectResponseDto.setClassId(classId);
            projectResponseDto.setTotalMember(projectClassMemberRepository.countByProjectId(project.getId()));
            return projectResponseDto;
        }).toList();
        return projectResponseDtos;
    }

    @Override
    public ProjectResponseDto addGithubData(Long projectId, GithubProjectRequestDto githubProjectRequestDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        String githubLink = githubProjectRequestDto.getGithubLink();
        String token = githubProjectRequestDto.getToken();
        if (githubLink == null || token == null) {
            throw new NullInformationException("Github link or token is null");
        }

        project.setGithubLink(githubLink);
        project.setToken(token);
        projectRepository.save(project);
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        projectResponseDto.setClassId(project.getClassroom().getId());
        return projectResponseDto;
    }


}
