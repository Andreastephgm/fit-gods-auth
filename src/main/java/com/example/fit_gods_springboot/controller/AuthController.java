package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.dto.AuthResponse;
import com.example.fit_gods_springboot.dto.LoginRequest;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.security.JwtUtil;
import com.example.fit_gods_springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil; // Inyectamos el utilitario de JWT

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        User newUser = userService.save(user);
        String token = jwtUtil.generateToken(newUser.getEmail());
        AuthResponse response = new AuthResponse(true, "Registro de usuario satisfactorio", token, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        User authenticatedUser = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        // Generamos el token JWT basado en el correo del usuario autenticado
        String token = jwtUtil.generateToken(authenticatedUser.getEmail());

        AuthResponse response = new AuthResponse(true, "Autenticación satisfactoria", token, authenticatedUser);
        return ResponseEntity.ok(response);
    }
}