package com.example.fit_gods_springboot;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User();
        user.setName("Maria");
        user.setEmail("maria.robles@fitgods.com");
        user.setPassword("123456");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("Maria", savedUser.getName());
    }
}
