package com.example.myproject.service;

import com.example.myproject.dto.UserDto;
import com.example.myproject.entity.User;

public interface UserService {

    User createUser(UserDto userDto);


    User getUserById(Integer id);

    void deleteUser(Integer id);

    User updateUser(Integer id, UserDto userDto);
}
