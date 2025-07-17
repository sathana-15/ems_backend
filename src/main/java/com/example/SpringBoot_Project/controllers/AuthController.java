package com.example.SpringBoot_Project.controllers;

import com.example.SpringBoot_Project.model.AuthResponse;
import com.example.SpringBoot_Project.model.RegisterDetails;
import com.example.SpringBoot_Project.model.UserDetailsDto;
import com.example.SpringBoot_Project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register){
        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody RegisterDetails login){
        AuthResponse response = authService.authenticate(login);
        return ResponseEntity.ok(response);
    }
}
