package com.example.my_project.controller;

import com.example.my_project.common.Result;
import com.example.my_project.dto.UserDto;
import com.example.my_project.entity.User;
import com.example.my_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result<User> add(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public Result<List<User>> getAllUser() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
