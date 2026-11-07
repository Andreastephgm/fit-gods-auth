package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.Routine;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.RoutineRepository;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// Anotación de Spring para indicar que esta clase pertenece a la capa de lógica de negocio
@Service
public class RoutineService {

    // Inyección de dependencias del repositorio de rutinas
    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    // Constructor para inyectar la dependencia
    public RoutineService(RoutineRepository routineRepository, UserRepository userRepository) {
        this.routineRepository = routineRepository;
        this.userRepository = userRepository;
    }

    // Método para guardar una nueva rutina en la base de datos
    public Routine save(Routine routine) {
        if (routine.getUser() != null && routine.getUser().getId() != null) {
            User userFromDb = userRepository.findById(routine.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + routine.getUser().getId()));

            // Asignamos la entidad User completa con todos sus campos cargados
            routine.setUser(userFromDb);
        }
        return routineRepository.save(routine);
    }

    // Método para obtener la lista de todas las rutinas registradas
    public List<Routine> findAll() {
        return routineRepository.findAll();
    }

    // Método para buscar una rutina por su id (clave primaria)
    public Routine findById(Long id) {

        return routineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rutina con ID: " + id));
    }

    // Método para consultar las rutinas asignadas a un usuario específico
    public List<Routine> findByUserId(Long idUser) {
        return routineRepository.findByUserId(idUser);
    }

    // Método para actualizar los datos de una rutina existente
    public Routine update(Routine routine) {
        return routineRepository.save(routine);
    }

    // Método para eliminar una rutina por su id
    public void deleteById(Long id) {
        routineRepository.deleteById(id);
    }
}
