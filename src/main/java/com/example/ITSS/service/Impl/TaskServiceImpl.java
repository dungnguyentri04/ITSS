package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.TaskRequestDto;
import com.example.ITSS.dto.responseDto.TaskResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.Task;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.TaskStatus;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.repositories.TaskRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        User assignee = userRepository.findByUserName(taskRequestDto.getAssignee());
        if (assignee == null) {
            throw new NotFoundException("Assignee not found");
        }
        Project project = projectRepository.findById(taskRequestDto.getProjectId()).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        // save task
        Task task = modelMapper.map(taskRequestDto, Task.class);
        task.setAssignee(assignee);
        task.setProject(project);
        task.setCreatedAt(LocalDate.now());
        task.setStatus(TaskStatus.IN_PROGRESS);
        Task saveTask = taskRepository.save(task);
        // save project
//        project.getTasks().add(saveTask);
//        projectRepository.save(project);
//        // save user
//        assignee.getTaskList().add(saveTask);
//        userRepository.save(assignee);
        // return
        TaskResponseDto taskResponseDto = modelMapper.map(saveTask, TaskResponseDto.class);
        taskResponseDto.setAssignee(taskRequestDto.getAssignee());
        taskResponseDto.setProjectId(taskRequestDto.getProjectId());
        return taskResponseDto;
    }

    @Override
    public String deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task not found")
        );
        taskRepository.delete(task);
        return "Task with ID " + id + " deleted successfully";
    }

    @Override
    public List<TaskResponseDto> getTasksByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        List<Task> tasks = project.getTasks();
        List<TaskResponseDto> taskResponseDtoList = tasks.stream().map(task -> {
            TaskResponseDto responseTask = modelMapper.map(task, TaskResponseDto.class);
            responseTask.setAssignee(task.getAssignee().getUserName());
            responseTask.setProjectId(projectId);
            return responseTask;
        }).toList();
        return taskResponseDtoList;
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public List<TaskResponseDto> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        List<Task> tasks = user.getTaskList();
        List<TaskResponseDto> taskResponseDtoList = tasks.stream().map(task -> {
            TaskResponseDto responseTask = modelMapper.map(task, TaskResponseDto.class);
            responseTask.setAssignee(user.getUserName());
            responseTask.setProjectId(task.getProject().getId());
            return responseTask;
        }).toList();
        return taskResponseDtoList;
    }

    @Override
    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task not found")
        );
        TaskResponseDto taskResponseDto = modelMapper.map(task, TaskResponseDto.class);
        taskResponseDto.setAssignee(task.getAssignee().getUserName());
        taskResponseDto.setProjectId(task.getProject().getId());
        return taskResponseDto;
    }
}
