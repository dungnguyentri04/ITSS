package com.example.ITSS.controllers;

import com.example.ITSS.config.OurUserInfoDetail;
import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.TaskRequestDto;
import com.example.ITSS.dto.responseDto.TaskResponseDto;
import com.example.ITSS.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/task/addTask")
    public ResponseEntity<ApiResponse<TaskResponseDto>> addTask(@RequestBody TaskRequestDto taskRequestDto, Authentication authentication) {
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        taskRequestDto.setCreatedBy(ourUserDetailService.getUsername());
        TaskResponseDto taskResponseDto = taskService.addTask(taskRequestDto);
        ApiResponse<TaskResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add task successfully");
        response.setData(taskResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/task/deleteTask")
    public ResponseEntity<ApiResponse<String>> deleteTask(@RequestParam("taskId") Long taskId){
        String message = taskService.deleteTaskById(taskId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Delete task successfully");
        response.setData(message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/task/getTaskProject")
    public ResponseEntity<?> getTaskProject(@RequestParam("projectId") Long projectId) {
        List<TaskResponseDto> taskResponseDtoList = taskService.getTasksByProjectId(projectId);
        ApiResponse<List<TaskResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get task successfully");
        response.setData(taskResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/task/getTaskUser")
    public ResponseEntity<?> getTaskUser(@RequestParam("userId") Long userId) {
        List<TaskResponseDto> taskResponseDtoList = taskService.getTasksByUserId(userId);
        ApiResponse<List<TaskResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get task successfully");
        response.setData(taskResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/task/getMyTasks")
    public ResponseEntity<?> getMyTasks(Authentication authentication) {
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        List<TaskResponseDto> taskResponseDtoList = taskService.getTasksByUserId(ourUserDetailService.getId());
        ApiResponse<List<TaskResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get task successfully");
        response.setData(taskResponseDtoList);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/task/updateTask")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(@RequestParam("taskId") Long taskId, @RequestBody TaskRequestDto taskRequestDto, Authentication authentication) {
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        Long userId = ourUserDetailService.getId();
        TaskResponseDto taskResponseDto = taskService.updateTask(taskId, taskRequestDto, userId);
        ApiResponse<TaskResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update task successfully");
        response.setData(taskResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
