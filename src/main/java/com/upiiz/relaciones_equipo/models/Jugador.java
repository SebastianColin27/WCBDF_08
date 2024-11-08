package com.upiiz.relaciones_equipo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private int edad;

    @NotBlank
    private String nacionalidad;

    @NotBlank
    private String posicion;

    @ManyToOne(targetEntity = Equipo.class)
    private Equipo equipo;
}
