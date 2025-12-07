package com.example.my_project.service.impl;

import com.example.my_project.common.Result;
import com.example.my_project.dto.UserDto;
import com.example.my_project.entity.User;
import com.example.my_project.repository.UserRepository;
import com.example.my_project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result<User> addUser(UserDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return Result.error("用户名已存在");
        }
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        userRepository.save(user1);
        return Result.success(user1);
    }

    @Override
    public Result<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(Result::success).orElseGet(() -> Result.error("用户名不存在"));
    }

    @Override
    public Result<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return Result.success(users);
    }

    @Override
    public Result<User> updateUser(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return Result.error("用户不存在");
        }
        User user1 = user.get();
        if (userDto.getUsername() != null && !user1.getUsername().equals(userDto.getUsername())) {
            if (userRepository.existsByUsername(userDto.getUsername())) {
                return Result.error("用户名已存在");
            }
        }
        BeanUtils.copyProperties(userDto, user1);
        user1.setId(id);
        User updatedUser = userRepository.save(user1);
        return Result.success(updatedUser);
    }

    @Override
    public Result<String> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return Result.error("用户不存在");
        }else {
            userRepository.deleteById(id);
            return Result.success("用户删除成功");
        }
    }
}
