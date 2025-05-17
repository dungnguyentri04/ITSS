package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.TaskRequestDto;
import com.example.ITSS.dto.responseDto.TaskResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    public String deleteTaskById(Long id);

    public List<TaskResponseDto> getTasksByProjectId(Long projectId);

    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    public List<TaskResponseDto> getTasksByUserId(Long userId);

    public TaskResponseDto findTaskById(Long id);
}
