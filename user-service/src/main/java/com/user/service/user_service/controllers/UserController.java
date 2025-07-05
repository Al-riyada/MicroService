package com.user.service.user_service.controllers;

import com.user.service.user_service.entities.User;
import com.user.service.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    
    // POST http://localhost:PORT/api/users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    
    // GET http://localhost:PORT/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    // GET http://localhost:PORT/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); 
    }


}
