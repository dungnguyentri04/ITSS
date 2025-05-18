package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.ClassRequestDto;
import com.example.ITSS.dto.responseDto.ClassResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.UserRole;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Override
    public ClassResponseDto addClass(ClassRequestDto classRequestDto) {
        User createdUser = userRepository.findById(classRequestDto.getUserCreatedId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        //save project
        Class aClass = modelMapper.map(classRequestDto, Class.class);
        aClass.setUserCreatedName(createdUser.getUserName());
        aClass.setCreatedAt(LocalDate.now());
        Class saveClass = classRepository.save(aClass);
        //response
        ClassResponseDto classResponseDto = modelMapper.map(saveClass, ClassResponseDto.class);
        return classResponseDto;
    }

    //check nhung nguoi duoc xem
    @Override
    public ClassResponseDto findClassById(Long id) {
        Class aClass = classRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        ClassResponseDto classResponseDto = modelMapper.map(aClass, ClassResponseDto.class);
        return classResponseDto;
    }

    @Override
    public String deleteClassById(Long id) {
        Class aClass = classRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        classRepository.delete(aClass);
        return "Project with ID " + id + " deleted successfully";
    }

    @Override
    public ClassResponseDto updateClass(Long id, ClassRequestDto classRequestDto) {
        return null;
    }

    @Override
    public List<ClassResponseDto> getAllClasses() {
        return null;
    }

    @Override
    public ClassResponseDto patchClass(Long id, ClassRequestDto classRequestDto) {
        return null;
    }

    @Override
    public List<ClassResponseDto> getClassByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        //role teacher
        if (user.getRole().equals(UserRole.TEACHER)) {
            List<Class> classes = classRepository.findAll();
            List<ClassResponseDto> classResponseDtos = new ArrayList<>();
            for (Class aClass : classes) {
                if (aClass.getUserCreatedName().equals(user.getUserName())) {
                    ClassResponseDto classResponseDto = modelMapper.map(aClass, ClassResponseDto.class);
                    classResponseDtos.add(classResponseDto);
                }
            }
            return classResponseDtos;
        }

        //role student
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByUserId(userId); //
        List<ClassResponseDto> classResponseDtos = projectClassMembers.stream().map(projectClassMember -> {
            Class aClass = projectClassMember.getAClass();
            ClassResponseDto classResponseDto = modelMapper.map(aClass, ClassResponseDto.class);
            return classResponseDto;
        }).toList();
        return classResponseDtos;
    }
}
