package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.dto.AuthResponse;
import com.example.fit_gods_springboot.dto.LoginRequest;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario
     * POST http://localhost:8080/api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        try {
            User newUser = userService.save(user);
            AuthResponse response = new AuthResponse(true, "Registro de usuario satisfactorio", newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(false, "Error en el registro: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Endpoint para inicio de sesión (autenticación)
     * POST http://localhost:8080/api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        // Se llama al método authenticate definido en UserService
        Optional<User> authenticatedUser = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (authenticatedUser.isPresent()) {
            AuthResponse response = new AuthResponse(true, "Autenticación satisfactoria", authenticatedUser.get());
            return ResponseEntity.ok(response);
        } else {
            AuthResponse response = new AuthResponse(false, "Error en la autenticación: Credenciales inválidas", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
