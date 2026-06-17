 package com.appointment.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appointment.backend.entity.User;
import com.appointment.backend.repository.UserRepository;
import com.appointment.backend.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // REGISTER USER
    public String registerUser(User user) {

        if (user == null) {
            return "Invalid User Data";
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return "Email Required";
        }

        if (repo.existsByEmail(user.getEmail())) {
            return "Email Already Exists";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Password Required";
        }

        // DEFAULT ROLE
        if (user.getRole() == null || user.getRole().isEmpty()) {

            user.setRole("USER");

        } else {

            user.setRole(
                user.getRole().toUpperCase()
            );

        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        repo.save(user);

        return "Registered Successfully";
    }

    // LOGIN USER
    public Object loginUser(User user) {

        User loggedUser = repo.findByEmail(user.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        boolean match = encoder.matches(
                user.getPassword(),
                loggedUser.getPassword()
        );

        if (!match) {
            return "INVALID";
        }

        System.out.println(
                "LOGIN ROLE = " +
                loggedUser.getRole()
        );

        String token = JwtUtil.generateToken(
                loggedUser.getEmail(),
                loggedUser.getRole()
        );

        Map<String, Object> data = new HashMap<>();

        data.put("token", token);
        data.put("role", loggedUser.getRole());
        data.put("name", loggedUser.getName());
        data.put("email", loggedUser.getEmail());

        return data;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // GET PROFILE
    public User getProfile(String email) {

        return repo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));
    }

    // GET USER BY ID
    public User getUserById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));
    }

    // UPDATE USER
    public User updateUser(Long id, User user) {

        User existingUser = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        existingUser.setName(user.getName());
        existingUser.setMobile(user.getMobile());
        existingUser.setEmail(user.getEmail());

        return repo.save(existingUser);
    }

    // DELETE USER
    public String deleteUser(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        repo.delete(user);

        return "User Deleted Successfully";
    }
}