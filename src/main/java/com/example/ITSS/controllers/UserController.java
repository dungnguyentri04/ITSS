package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.UserRequestDto;
import com.example.ITSS.dto.responseDto.UserResponseDto;
import com.example.ITSS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(){
        return null;
    }

    @PostMapping("/users/addUser")
    public ResponseEntity<ApiResponse<UserResponseDto>> addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userDto = userService.addUser(userRequestDto);
        ApiResponse<UserResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add user successfully");
        response.setData(userDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/users/deleteUser/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long userId){
        String message = userService.deleteUserById(userId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Delete user successfully");
        response.setData(message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/users/updateUser/{userId}")
    public String updateUser(){
        return null;
    }

    @GetMapping("/users/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<UserResponseDto> userDtoList = userService.getAllUser();
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total", userDtoList.size());
        apiResponse.setStatus("success");
        apiResponse.setMessage("All users");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(userDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/users/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        UserResponseDto userDto = userService.findUserById(userId);
        ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Select user successfully");
        apiResponse.setMetadata(null);
        apiResponse.setData(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/users/{userId}/forget-password")
    public String forgetPassword(){
        return null;
    }

    @GetMapping("/users/getUsersProject")
    public ResponseEntity<?> getUserProject(@RequestParam("projectId") Long projectId){

        return null;
    }

    @GetMapping("/users/getUsersByClassId")
    public ResponseEntity<?> getUserByClassId(@RequestParam("classId") Long classId){
        List<UserResponseDto> userDtoList = userService.getUsersByClassId(classId);
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total", userDtoList.size());
        apiResponse.setStatus("success");
        apiResponse.setMessage("All users");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(userDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/users/getUsersByProjectId")
    public ResponseEntity<?> getUserByProjectId(@RequestParam("projectId") Long projectId){
        List<UserResponseDto> userDtoList = null;
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("All users");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(userDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/users/getUsersByUserId")
    public ResponseEntity<?> getUserByUserId(@RequestParam("userId") Long userId){
        List<UserResponseDto> userDtoList =null;
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("All users");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(userDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/users/getUsersByUserIdAndProjectId")
    public ResponseEntity<?> getUserByUserIdAndProjectId(@RequestParam("userId") Long userId, @RequestParam("projectId") Long projectId){
        List<UserResponseDto> userDtoList = null;
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
        Map<String, Object> metadata = new HashMap<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("All users");
        apiResponse.setMetadata(metadata);
        apiResponse.setData(userDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
