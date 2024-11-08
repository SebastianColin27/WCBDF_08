package com.upiiz.relaciones_equipo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nombre;

    @OneToOne(targetEntity = Entrenador.class)
    private Entrenador entrenador;

    @ManyToOne(targetEntity = Liga.class)
    private Liga liga;

  //  @OneToMany(targetEntity = Jugador.class, fetch = FetchType.LAZY)
  //  private List<Jugador> jugadores;

    @ManyToMany(targetEntity = Competencia.class, fetch = FetchType.LAZY)
    private List<Competencia> competencias;
}
