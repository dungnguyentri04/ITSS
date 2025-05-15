package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.UserRequestDto;
import com.example.ITSS.dto.responseDto.UserResponseDto;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> getAllUser();

    public UserResponseDto findUserById(Long id);

    public String deleteUserById(Long id);

    public UserResponseDto addUser(UserRequestDto userDto);
}
