package com.example.fit_gods_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO para estructurar las respuestas de login y registro
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private boolean success;
    private String message;
    private Object user; //para retornar datos del usuario si la autenticación es correcta
}