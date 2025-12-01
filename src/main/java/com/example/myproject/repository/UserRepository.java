package com.example.myproject.repository;

import com.example.myproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    //检查用户是否存在
    boolean existsByUsername(String username);
}
