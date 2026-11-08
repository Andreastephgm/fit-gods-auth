package com.example.fit_gods_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Anotaciones de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

// Anotaciones de JPA / Base de datos
@Entity
@Table(name = "routineExercises")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoutineExercise") // Primary Key
    private Long idRoutineExercise;

    @Column(name = "exerciseName", nullable = false)
    private String exerciseName;

    private Long idExercise;
    private Integer series;
    private Integer repetitions;
    private Integer rest;

    // Relación ManyToOne con Cascade para evitar TransientPropertyValueException
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idRoutine", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotBlank(message = "Error: Todo ejercicio debe estar asociado a una rutina!")
    private Routine routine;
}