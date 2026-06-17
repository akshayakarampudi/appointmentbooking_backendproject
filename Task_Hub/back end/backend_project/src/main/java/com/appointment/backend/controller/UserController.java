 package com.appointment.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.appointment.backend.entity.User;
import com.appointment.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(
            @Valid
            @RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Object loginUser(
            @RequestBody User user) {

        return userService.loginUser(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER','ADMIN','DOCTOR')")
    public User getProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return userService.getProfile(email);
    }
}