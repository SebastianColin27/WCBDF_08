package com.upiiz.relaciones_equipo.repositories;

import com.upiiz.relaciones_equipo.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
