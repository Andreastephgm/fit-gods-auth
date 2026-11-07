package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.Progress;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.ProgressRepository;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository; // Inyectamos UserRepository para verificar y consultar el usuario

    public ProgressService(ProgressRepository progressRepository, UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
    }

    public Progress save(Progress progress) {
        // Carga la entidad User persistida de MySQL antes de guardar
        if (progress.getUser() != null && progress.getUser().getId() != null) {
            User userFromDb = userRepository.findById(progress.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + progress.getUser().getId()));
            progress.setUser(userFromDb);
        }
        return progressRepository.save(progress);
    }

    public List<Progress> findAll() {
        return progressRepository.findAll();
    }

    public Progress findById(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Progreso con ID: " + id));
    }

    public List<Progress> findByUserId(Long idUser) {
        return progressRepository.findByUserId(idUser);
    }

    public Progress update(Progress progress) {
        // Reutiliza la lógica de save para garantizar la asociación con un usuario existente
        return progressRepository.save(progress);
    }

    public void deleteById(Long id) {
        progressRepository.deleteById(id);
    }
}