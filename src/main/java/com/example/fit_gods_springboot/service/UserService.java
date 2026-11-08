package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("El usuario dene tener un email!");
        } else if (user.getPassword() == null) {
            throw new IllegalArgumentException("El usuario dene tener un password!");
        }else{
            return userRepository.save(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new  NoSuchElementException("Usuario con ID: " + id));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Autenticación
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new org.springframework.security.authentication.BadCredentialsException("Credenciales inválidas o usuarion no registrado") );

        if (!user.getPassword().equals(password)) {
            throw  new org.springframework.security.authentication.BadCredentialsException("Contraseña incorrecta.");
        }

        return user;
    }

    public User update(User user) {
        User existingUser = findById(user.getId());
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
