package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.Progress;
import com.example.fit_gods_springboot.repository.ProgressRepository;
import com.example.fit_gods_springboot.service.ProgressService;
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
class ProgressServiceTest {

    @Mock
    private ProgressRepository progressRepository;

    @InjectMocks
    private ProgressService progressService;

    @Test
    void testSaveProgress() {
        Progress progress = new Progress();
        progress.setIdProgress(1L);

        when(progressRepository.save(any(Progress.class))).thenReturn(progress);

        Progress saved = progressService.save(progress);

        assertNotNull(saved);
        assertEquals(1L, saved.getIdProgress());
        verify(progressRepository, times(1)).save(progress);
    }

    @Test
    void testFindAllProgress() {
        Progress p1 = new Progress();
        Progress p2 = new Progress();

        when(progressRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Progress> list = progressService.findAll();

        assertEquals(2, list.size());
        verify(progressRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        Progress progress = new Progress();
        progress.setIdProgress(1L);

        when(progressRepository.findById(1L)).thenReturn(Optional.of(progress));

        Optional<Progress> result = progressService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdProgress());
        verify(progressRepository, times(1)).findById(1L);
    }
}
