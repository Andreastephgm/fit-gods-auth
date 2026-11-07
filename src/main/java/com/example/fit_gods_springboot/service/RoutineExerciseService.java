package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.Routine;
import com.example.fit_gods_springboot.model.RoutineExercise;
import com.example.fit_gods_springboot.repository.RoutineExerciseRepository;
import com.example.fit_gods_springboot.repository.RoutineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoutineExerciseService {

    private final RoutineExerciseRepository routineExerciseRepository;
    private final RoutineRepository routineRepository; // 1. Inyección de RoutineRepository

    // Constructor con ambas dependencias
    public RoutineExerciseService(RoutineExerciseRepository routineExerciseRepository,
                                  RoutineRepository routineRepository) {
        this.routineExerciseRepository = routineExerciseRepository;
        this.routineRepository = routineRepository;
    }

    // Guardar o actualizar un ejercicio de rutina
    public RoutineExercise save(RoutineExercise routineExercise) {
        if (routineExercise.getRoutine() != null) {
            // Nota: Si en tu modelo Routine la clave primaria es idRoutine usa getIdRoutine(), de lo contrario getId()
            Long routineId = routineExercise.getRoutine().getIdRoutine();

            if (routineId != null) {
                Routine routineFromDb = routineRepository.findById(routineId)
                        .orElseThrow(() -> new NoSuchElementException("Rutina no encontrada con ID: " + routineId));

                // Asignamos la rutina persistida de la base de datos
                routineExercise.setRoutine(routineFromDb);
            }
        }
        return routineExerciseRepository.save(routineExercise);
    }

    // Listar todos los ejercicios de rutina
    public List<RoutineExercise> findAll() {
        return routineExerciseRepository.findAll();
    }

    // Buscar por ID del registro (idRoutineExercise)
    public RoutineExercise findById(Long id) {

        return routineExerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ejercicio con ID: " + id));
    }

    // Listar todos los ejercicios asociados a una rutina
    public List<RoutineExercise> findByRoutineId(Long idRoutine) {
        return routineExerciseRepository.findByRoutineIdRoutine(idRoutine);
    }

    // Buscar por ID de rutina e ID de ejercicio
    public List<RoutineExercise> findByRoutineAndExercise(Long idRoutine, Long idExercise) {
        return routineExerciseRepository.findByRoutineIdRoutineAndIdExercise(idRoutine, idExercise);
    }

    // Eliminar por ID del registro
    public void deleteById(Long id) {
        routineExerciseRepository.deleteById(id);
    }
}