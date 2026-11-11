package com.example.fit_gods_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
//Anotaciones de lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "progress")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProgress")
    private Long idProgress;

    private LocalDate date;
    private Double weight;
    private String notes;

    // Se especifica la relacion many to one
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idUser", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "El progreso debe estar asociado a un usuario!")
    private User user;
}