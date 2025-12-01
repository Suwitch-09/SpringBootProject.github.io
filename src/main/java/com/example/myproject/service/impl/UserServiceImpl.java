package com.example.myproject.service.impl;

import com.example.myproject.dto.UserDto;
import com.example.myproject.entity.User;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("用户名已存在: " + userDto.getUsername());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("用户id为" + id));
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(Integer id, UserDto userDto) {
        User existingUser = getUserById(id);

        if (!existingUser.getUsername().equals(userDto.getUsername()) && userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("用户名已存在: " + userDto.getUsername());
        }
        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(userDto.getPassword());

        return userRepository.save(existingUser);
    }
}
