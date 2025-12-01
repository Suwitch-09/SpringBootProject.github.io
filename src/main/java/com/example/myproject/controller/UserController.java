package com.example.myproject.controller;

import com.example.myproject.dto.UserDto;
import com.example.myproject.entity.User;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    //创建用户
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    //根据id查找用户
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    //更改用户
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
}
