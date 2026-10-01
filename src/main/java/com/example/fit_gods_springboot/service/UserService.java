package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Método de autenticación requerido para la evidencia AA5-EV01
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        // Verifica si el usuario existe y coincide la contraseña
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional;
        }

        return Optional.empty(); // Falla la autenticación
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
