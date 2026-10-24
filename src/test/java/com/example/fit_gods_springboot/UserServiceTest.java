package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.UserRepository;
import com.example.fit_gods_springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    // Simula el repositorio para no conectarse a la base de datos real
    @Mock
    private UserRepository userRepository;

    // Inyecta el repositorio simulado dentro del servicio que vamos a probar
    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {
        // 1. Arrange (Preparar datos)
        User user = new User(1L, "Maria", "Robles", "maria.robles@fitgods.com", "123456", LocalDate.of(2000, 5, 15), 62.0, 1.70, "Ganar masa muscular");

        // Simulamos que al guardar el usuario en el repositorio, devuelve el usuario con éxito
        when(userRepository.save(any(User.class))).thenReturn(user);

        // 2. Act (Ejecutar el método del servicio)
        User savedUser = userService.save(user);

        // 3. Assert (Verificar resultados)
        assertNotNull(savedUser);
        assertEquals("Maria", savedUser.getName());
        assertEquals("maria.robles@fitgods.com", savedUser.getEmail());

        // Verificamos que el repositorio fue llamado exactamente una vez
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindAllUsers() {
        // Arrange
        User user1 = new User(1L, "Maria", "Robles", "maria.robles@fitgods.com", "123456", LocalDate.of(2000, 5, 15), 62.0, 1.70, "Ganar masa muscular");
        User user2 = new User(2L, "Carlos", "Pérez", "carlos@fitgods.com", "654321", LocalDate.of(1995, 8, 20), 75.0, 1.80, "Perder grasa");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        List<User> users = userService.findAll();

        // Assert
        assertEquals(2, users.size());
        assertEquals("Maria", users.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        User user = new User(1L, "Maria", "Robles", "maria.robles@fitgods.com", "123456", LocalDate.of(2000, 5, 15), 62.0, 1.70, "Ganar masa muscular");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.delete(userId);

        // Assert
        // Verificamos que se ejecutó la orden de eliminar en el repositorio
        verify(userRepository, times(1)).deleteById(userId);
    }
}
