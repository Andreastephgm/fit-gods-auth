package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.Routine;
import com.example.fit_gods_springboot.service.RoutineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anotación para indicar que es un controlador REST y los retornos serán serializados a JSON
@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    // Inyección de dependencias de la capa de servicio
    private final RoutineService routineService;

    // Constructor para inyectar la dependencia
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    // Endpoint para guardar una nueva rutina
    // POST: http://localhost:8080/api/routines/new
    @PostMapping("/new")
    public ResponseEntity<Routine> save(@Valid @RequestBody Routine routine) {
        if (routine.getUser() == null || routine.getUser().getId() == null) {
            throw new IllegalArgumentException("La rutina debe estar asociada a un usuario válido con su ID.");
        }else {
            Routine newRoutine = routineService.save(routine);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRoutine);
        }
    }

    // Endpoint para obtener todas las rutinas registradas
    // GET: http://localhost:8080/api/routines/all
    @GetMapping("/all")
    public ResponseEntity<List<Routine>> findAll() {
        return ResponseEntity.ok(routineService.findAll());
    }

    // Endpoint para buscar una rutina por su id (clave primaria)
    // GET: http://localhost:8080/api/routines/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Routine> findById(@PathVariable Long id) {
        Routine routine = routineService.findById(id);
        return ResponseEntity.ok(routine);
    }

    // Endpoint para buscar todas las rutinas de un usuario específico
    // GET: http://localhost:8080/api/routines/user/{idUser}
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Routine>> findByUserId(@PathVariable Long idUser) {
        List<Routine> routines = routineService.findByUserId(idUser);
        return ResponseEntity.ok(routines);
    }

    // Endpoint para actualizar una rutina existente
    // PUT: http://localhost:8080/api/routines/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<Routine> update(@PathVariable Long id, @RequestBody Routine routineDetails) {
        Routine existingRoutine = routineService.findById(id);

        existingRoutine.setName(routineDetails.getName());
        existingRoutine.setObjective(routineDetails.getObjective());

        Routine routineSave = routineService.save(existingRoutine);

        return ResponseEntity.ok(routineSave);
    }

    // Endpoint para eliminar una rutina por su id
    // DELETE: http://localhost:8080/api/routines/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        routineService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
