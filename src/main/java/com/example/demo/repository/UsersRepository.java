package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

    
}