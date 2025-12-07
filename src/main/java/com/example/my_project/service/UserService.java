package com.example.my_project.service;

import com.example.my_project.common.Result;
import com.example.my_project.dto.UserDto;
import com.example.my_project.entity.User;

import java.util.List;

public interface UserService {

    Result<User> addUser(UserDto user);

    Result<User> getUserById(Long id);

    Result<List<User>> getAllUsers();

    Result<User> updateUser(Long id, UserDto userDto);

    Result deleteUser(Long id);
}
