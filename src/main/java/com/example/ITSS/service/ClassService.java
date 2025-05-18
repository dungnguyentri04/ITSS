package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ClassResponseDto;

import java.util.List;

public interface ClassService {
    public ClassResponseDto addClass(ClassRequestDto classRequestDto);

    public ClassResponseDto findClassById(Long id);

    public String deleteClassById(Long id);

    public ClassResponseDto updateClass(Long id, ClassRequestDto classRequestDto);

    public List<ClassResponseDto> getAllClasses();

    public ClassResponseDto patchClass(Long id, ClassRequestDto classRequestDto);

    public List<ClassResponseDto> getClassByUserId(Long userId);
}
