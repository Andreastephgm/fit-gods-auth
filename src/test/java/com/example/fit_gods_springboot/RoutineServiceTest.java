package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.Routine;
import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.RoutineRepository;
import com.example.fit_gods_springboot.repository.UserRepository;
import com.example.fit_gods_springboot.service.RoutineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoutineServiceTest {

    @Mock
    private RoutineRepository routineRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RoutineService routineService;

    @Test
    void testSaveRoutine() {
        User user = new User();
        user.setId(1L);

        Routine routine = new Routine();
        routine.setIdRoutine(1L);
        routine.setName("Rutina de Piernas");
        routine.setObjective("Hipertrofia");
        routine.setUser(user);

        // Simulamos que el repositorio encuentra el usuario y guarda la rutina
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(routineRepository.save(any(Routine.class))).thenReturn(routine);

        Routine savedRoutine = routineService.save(routine);

        assertNotNull(savedRoutine);
        assertEquals("Rutina de Piernas", savedRoutine.getName());
        assertEquals("Hipertrofia", savedRoutine.getObjective());
        verify(routineRepository, times(1)).save(routine);
    }

    @Test
    void testFindAllRoutines() {
        Routine routine1 = new Routine();
        routine1.setName("Piernas");

        Routine routine2 = new Routine();
        routine2.setName("Pecho y Tríceps");

        when(routineRepository.findAll()).thenReturn(List.of(routine1, routine2));

        List<Routine> routines = routineService.findAll();

        assertEquals(2, routines.size());
        assertEquals("Piernas", routines.get(0).getName());
        verify(routineRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        Routine routine = new Routine();
        routine.setIdRoutine(1L);
        routine.setName("Espalda");

        when(routineRepository.findById(1L)).thenReturn(Optional.of(routine));

        Optional<Routine> result = routineService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Espalda", result.get().getName());
        verify(routineRepository, times(1)).findById(1L);
    }
}