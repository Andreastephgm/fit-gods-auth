package com.example.fit_gods_springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

//Anotaciones de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//anotaciones bd
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")//primary key
    private Long id;
//atributos
    private String name;
    private String surname;
    @NotBlank(message = "El email no puede estar en blanco!")
    private String email;
    @NotBlank(message = "El password no puede estar en blanco!")
    private String password;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    private Double weight;
    private Double height;
    private String objective;

}
