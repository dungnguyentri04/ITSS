package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.UserRequestDto;
import com.example.ITSS.dto.responseDto.UserResponseDto;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.enums.UserRole;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.User;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserResponseDto> userDtoList = userRepository.findAll().stream().map(
                user -> {
                    UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
                    userDto.setRole(user.getRole().name());
                    return userDto;
                }
        ).toList();
        if (userDtoList.isEmpty()) throw new NotFoundException("Not found any user");
        return userDtoList;
    }

    @Override
    public UserResponseDto findUserById(Long id)  {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user"));
        UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
        userDto.setRole(user.getRole().name());
        return userDto;
    }

    @Override
    public String deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        userRepository.delete(user);
        return "User with ID " + id + " deleted successfully";
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User existingUser = userRepository.findByUserName(userRequestDto.getUserName());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }
        //check user ton tai
        String password = userRequestDto.getPassword();
        String confirmPassword = userRequestDto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password do not match");
        }
        User user = modelMapper.map(userRequestDto, User.class);
        UserRole role = UserRole.valueOf(userRequestDto.getRole());
        user.setRole(role);
        user.setCreated_at(LocalDate.now());
        User saveUser = userRepository.save(user);
        UserResponseDto userResponseDto = modelMapper.map(saveUser, UserResponseDto.class);
        userResponseDto.setRole(saveUser.getRole().name());
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getUsersByClassId(Long classId) {
        Class aClass = classRepository.findById(classId).orElseThrow(
                () -> new NotFoundException("Class not found")
        );
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByClassroomId(classId);
        List<UserResponseDto> userResponseDtos = projectClassMembers.stream().map(projectClassMember -> {
            User user = projectClassMember.getUser();
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            userResponseDto.setRole(user.getRole().name());
            return userResponseDto;
        }).toList();
        return userResponseDtos;
    }

}
