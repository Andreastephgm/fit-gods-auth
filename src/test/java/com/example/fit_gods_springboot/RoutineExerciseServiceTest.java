package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.RoutineExercise;
import com.example.fit_gods_springboot.repository.RoutineExerciseRepository;
import com.example.fit_gods_springboot.service.RoutineExerciseService;
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
class RoutineExerciseServiceTest {

    @Mock
    private RoutineExerciseRepository routineExerciseRepository;

    @InjectMocks
    private RoutineExerciseService routineExerciseService;

    @Test
    void testSaveRoutineExercise() {
        RoutineExercise routineExercise = new RoutineExercise();
        routineExercise.setIdRoutineExercise(1L);

        when(routineExerciseRepository.save(any(RoutineExercise.class))).thenReturn(routineExercise);

        RoutineExercise saved = routineExerciseService.save(routineExercise);

        assertNotNull(saved);
        assertNotNull(saved);
        verify(routineExerciseRepository, times(1)).save(any(RoutineExercise.class));
    }

    @Test
    void testFindAllRoutineExercises() {
        RoutineExercise re1 = new RoutineExercise();
        RoutineExercise re2 = new RoutineExercise();

        when(routineExerciseRepository.findAll()).thenReturn(List.of(re1, re2));

        List<RoutineExercise> list = routineExerciseService.findAll();

        assertEquals(2, list.size());
        verify(routineExerciseRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        RoutineExercise routineExercise = new RoutineExercise();
        routineExercise.setIdRoutineExercise(1L);

        when(routineExerciseRepository.findById(1L)).thenReturn(Optional.of(routineExercise));

        Optional<RoutineExercise> result = routineExerciseService.findById(1L);

        assertTrue(result.isPresent());
        assertNotNull(result.get());
        verify(routineExerciseRepository, times(1)).findById(1L);
    }
}
