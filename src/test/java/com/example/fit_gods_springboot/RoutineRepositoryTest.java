package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.Routine;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.RoutineRepository;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoutineRepositoryTest {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindRoutine() {
        // Como la rutina requiere un usuario obligatorio (Foreign Key), lo creamos y guardamos primero
        User user = new User();
        user.setName("Maria");
        user.setEmail("maria.robles@fitgods.com");
        user.setPassword("123456");
        User savedUser = userRepository.save(user);

        // Creamos la rutina asociada al usuario
        Routine routine = new Routine();
        routine.setName("Full Body");
        routine.setObjective("Fuerza");
        routine.setUser(savedUser);

        Routine savedRoutine = routineRepository.save(routine);

        // Verificaciones
        assertNotNull(savedRoutine.getIdRoutine());
        assertEquals("Full Body", savedRoutine.getName());
        assertEquals("Fuerza", savedRoutine.getObjective());
        assertNotNull(savedRoutine.getUser());
    }
}
