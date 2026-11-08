package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registrar un nuevo usuario
    // POST: http://localhost:8080/api/users/new
    @PostMapping("/new")
    public ResponseEntity<User> save(@RequestBody User user) {
        User newUser = userService.save(user);
        if(user.getEmail() == null){
            throw new IllegalArgumentException("El usuario dene tener un email!");
        } else if (user.getPassword() == null) {
            throw new IllegalArgumentException("El usuario dene tener un password!");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }

    }

    // Listar todos los usuarios
    // GET: http://localhost:8080/api/users/all
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Buscar un usuario por ID
    // GET: http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // Actualizar un usuario existente
    // PUT: http://localhost:8080/api/users/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userDetails) {
        User existingUser = userService.findById(id);
        existingUser.setName(userDetails.getName());
        existingUser.setSurname(userDetails.getSurname());
        existingUser.setHeight(userDetails.getHeight());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setDateOfBirth(userDetails.getDateOfBirth());
        existingUser.setWeight(userDetails.getWeight());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setObjective(userDetails.getObjective());

        User userUpdate = userService.save(existingUser);
        return ResponseEntity.ok(userUpdate);
    }

    // Eliminar un usuario por ID
    // DELETE: http://localhost:8080/api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}