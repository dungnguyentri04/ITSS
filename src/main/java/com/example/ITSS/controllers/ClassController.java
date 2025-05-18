package com.example.ITSS.controllers;

import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ClassResponseDto;
import com.example.ITSS.service.GithubService;
import com.example.ITSS.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private GithubService githubService;

    @PostMapping("/class/addClass")
    public ResponseEntity<ApiResponse<ClassResponseDto>> addClass(@RequestBody ClassRequestDto classRequestDto) {
        ClassResponseDto classResponseDto = classService.addClass(classRequestDto);
        ApiResponse<ClassResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("add class successfully");
        response.setData(classResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("class/getAllClasses")
    public ResponseEntity<?> getAllClasses() {
        List<ClassResponseDto> allClasses = classService.getAllClasses();
        ApiResponse<List<ClassResponseDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get all classes successfully");
        response.setData(allClasses);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("class/getClass")
    public ResponseEntity<?> getClass(@RequestParam("classId") Long classId) {
        ClassResponseDto classResponseDto = classService.findClassById(classId);
        ApiResponse<ClassResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("get class successfully");
        response.setData(classResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("class/updateClass")
    public ResponseEntity<?> updateClass(@RequestParam("classId") Long classId, @RequestBody ClassRequestDto classRequestDto) {
        ClassResponseDto classResponseDto = classService.updateClass(classId, classRequestDto);
        ApiResponse<ClassResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("update class successfully");
        response.setData(classResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //them link github
    @PatchMapping("class/patchClass")
    public ResponseEntity<?> patchClass(@RequestParam("classId") Long classId, @RequestBody ClassRequestDto classRequestDto) {
        ClassResponseDto classResponseDto = classService.patchClass(classId, classRequestDto);
        ApiResponse<ClassResponseDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("patch class successfully");
        response.setData(classResponseDto);
        response.setMetadata(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}